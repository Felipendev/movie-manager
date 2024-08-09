package com.movie.manager.movie_manager.episode.application.api;

import com.movie.manager.movie_manager.episode.application.response.EpisodeResponse;
import com.movie.manager.movie_manager.episode.application.response.SeasonResponse;
import com.movie.manager.movie_manager.episode.application.service.SearchEpisodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class SearchEpisodeRestController implements SearchEpisodeAPI {
    private final SearchEpisodeService searchEpisodeService;

    @Override
    public SeasonResponse getEpisodes(String title, int season) {
        log.info("[start] SearchEpisodeRestController - getEpisodes");
        SeasonResponse episode = searchEpisodeService.findByTitle(title, season);
        log.info("[finish] SearchEpisodeRestController - getEpisodes");
        return episode;
    }

    @Override
    public List<EpisodeResponse> getAllEpisodes(String title, int season) {
        log.info("[start] SearchEpisodeRestController - getAllEpisodes");
        List<EpisodeResponse> episodeList = searchEpisodeService.getEpisodeList(title, season);
        log.info("[finish] SearchEpisodeRestController - getAllEpisodes");
        return episodeList;
    }

    @Override
    public List<EpisodeResponse> getEpisodesAfterDate(String title, int totalSeasons, LocalDate date) {
        log.info("[start] SearchEpisodeRestController - findByDate");
        List<EpisodeResponse> episodesByDate = searchEpisodeService.getEpisodesAfterDate(title, totalSeasons, date);
        log.info("[finish] SearchEpisodeRestController - findByDate");
        return episodesByDate;
    }

    @Override
    public List<EpisodeResponse> getTopFiveEpisodes(String title, int totalSeasons) {
        log.info("[start] SearchEpisodeRestController - getTopFiveEpisodes");
        List<EpisodeResponse> episodesByRating = searchEpisodeService.getEpisodesByRating(title, totalSeasons);
        log.info("[finish] SearchEpisodeRestController - getTopFiveEpisodes");
        return episodesByRating;
    }

    @Override
    public List<String> getTopSeasons(String title, int totalSeasons) {
        log.info("[start] SearchEpisodeRestController - getTopSeasons");
        List<String> topSeasons = searchEpisodeService.getTopSeasons(title, totalSeasons);
        log.info("[finish] SearchEpisodeRestController - getTopSeasons");
        return topSeasons;
    }

    @Override
    public String getSeasonByTitle(String episodeTitle, int totalSeasons, String title) {
        log.info("[start] SearchEpisodeRestController - getSeasonByTitle");
        String seasonNumber = searchEpisodeService.findSeasonByEpisodeTitle(episodeTitle, totalSeasons, title);
        log.info("[finish] SearchEpisodeRestController - getSeasonByTitle");
        return seasonNumber;
    }

}
