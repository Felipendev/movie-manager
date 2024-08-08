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

}
