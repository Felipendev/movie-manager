package com.movie.manager.movie_manager.episode.application.service;

import com.movie.manager.movie_manager.episode.application.response.EpisodeResponse;
import com.movie.manager.movie_manager.episode.application.response.SeasonResponse;
import com.movie.manager.movie_manager.episode.domain.Episode;
import com.movie.manager.movie_manager.infra.api.EpisodeFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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
