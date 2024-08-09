package com.movie.manager.movie_manager.episode.application.service;

import com.movie.manager.movie_manager.episode.application.response.EpisodeResponse;
import com.movie.manager.movie_manager.episode.application.response.SeasonResponse;
import com.movie.manager.movie_manager.episode.domain.Episode;
import com.movie.manager.movie_manager.handler.APIException;
import com.movie.manager.movie_manager.infra.api.EpisodeFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Log4j2
public class SearchEpisodeApplicationService implements SearchEpisodeService {
    private final EpisodeFeignClient episodeFeignClient;

    @Value("${omdb.episode.apikey}")
    private String API_KEY;

    @Override
    public SeasonResponse findByTitle(String title, int season) {
        log.info("[start] SearchEpisodeApplicationService - findByTitle");
        SeasonResponse episode = episodeFeignClient.findByTitle(API_KEY, title, season);
        log.info("[finish] SearchEpisodeApplicationService - findByTitle");
        return episode;
    }

    @Override
    public List<EpisodeResponse> getEpisodeList(String title, int totalSeasons) {
        log.info("[start] SearchEpisodeApplicationService - getEpisodeList");
        List<SeasonResponse> seasons = getSeasonResponses(title, totalSeasons);
        List<EpisodeResponse> episodes = extractEpisodesFromSeasons(seasons);
        log.info("[finish] SearchEpisodeApplicationService - getEpisodeList");
        return episodes;
    }

    @Override
    public List<EpisodeResponse> getEpisodesAfterDate(String title, int totalSeasons, LocalDate date) {
        log.info("[start] SearchEpisodeApplicationService - findEpisodesBydate");
        List<SeasonResponse> seasons = getSeasonResponses(title, totalSeasons);
        List<EpisodeResponse> episodes = extractEpisodesFromSeasons(seasons).stream()
                .filter(episodeResponse -> {
                    Episode episode = new Episode(seasons.get(0).numero(), episodeResponse);
                    return episode.getYeah() != null && episode.getYeah().isAfter(date);
                })
                .collect(Collectors.toList());
        log.info("[finish] SearchEpisodeApplicationService - findEpisodesBydate");
        return episodes;
    }

    @Override
    public List<EpisodeResponse> getEpisodesByRating(String title, int totalSeasons) {
        log.info("[start] SearchEpisodeApplicationService - getEpisodesByRating");
        List<SeasonResponse> seasons = getSeasonResponses(title, totalSeasons);
        List<Episode> episodes = seasons.stream()
                .flatMap(seasonResponse -> seasonResponse.episodios().stream()
                        .map(episodeResponse -> new Episode(seasonResponse.numero(), episodeResponse)))
                .toList();

        List<EpisodeResponse> topFiveEpisodes = episodes.stream()   
                .sorted(Comparator.comparing(Episode::getRating).reversed())
                .limit(5)
                .map(EpisodeResponse::fromEpisode)
                .collect(Collectors.toList());
        log.info("[finish] SearchEpisodeApplicationService - getEpisodesByRating");
        return topFiveEpisodes;
    }

    @Override
    public String findSeasonByEpisodeTitle(String episodeTitle, int totalSeasons, String title) {
        log.info("[start] SearchEpisodeApplicationService - findSeasonByEpisodeTitle");
        List<SeasonResponse> seasons = getSeasonResponses(title, totalSeasons);
        Optional<Episode> matchingEpisode = seasons.stream()
                        .flatMap(seasonResponse -> seasonResponse.episodios().stream()
                                .map(episodeResponse -> new Episode(seasonResponse.numero(), episodeResponse)))
                                .filter(episode -> episode.getTitle().equalsIgnoreCase(episodeTitle))
                                        .findFirst();
        log.info("[finish] SearchEpisodeApplicationService - findSeasonByEpisodeTitle");
        return matchingEpisode.map(episode -> "Season: " + episode.getSeason()).orElseThrow(() ->
                APIException.build(HttpStatus.NOT_FOUND, "Episode not found"));
    }

    @Override
    public List<String> getTopSeasons(String title, int totalSeasons) {
        log.info("[start] SearchEpisodeApplicationService - getTopSeasons");
        List<SeasonResponse> seasons = getSeasonResponses(title, totalSeasons);
        List<String> topSeasons = seasons.stream()
                .map(this::calculateAverageRating)
                .sorted(Map.Entry.<Integer, Double>comparingByValue().reversed())
                .limit(3)
                .map(entry -> "Temporada: " + entry.getKey() + " - Media: " + entry.getValue())
                .collect(Collectors.toList());
        log.info("[finish] SearchEpisodeApplicationService - getTopSeasons");
        return topSeasons;
    }

    private AbstractMap.SimpleEntry<Integer, Double> calculateAverageRating(SeasonResponse season) {
        double averageRating = season.episodios().stream()
                .mapToDouble(this::parseRating)
                .average()
                .orElse(0.0);
        return new AbstractMap.SimpleEntry<>(season.numero(), averageRating);
    }

    private double parseRating(EpisodeResponse episode) {
        try {
            return Double.parseDouble(episode.rating());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private List<SeasonResponse> getSeasonResponses(String title, int totalSeasons) {
        return IntStream.rangeClosed(1, totalSeasons)
                .mapToObj(seasonNum -> episodeFeignClient.findByTitle(API_KEY, title, seasonNum))
                .collect(Collectors.toList());
    }

    private List<EpisodeResponse> extractEpisodesFromSeasons(List<SeasonResponse> seasons) {
        return seasons.stream()
                .flatMap(seasonResponse -> seasonResponse.episodios().stream()
                        .map(episode -> new Episode(seasonResponse.numero(), episode))
                        .map(EpisodeResponse::fromEpisode))
                .collect(Collectors.toList());
    }
}
