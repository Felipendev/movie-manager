package com.movie.manager.movie_manager.episode.application.service;

import com.movie.manager.movie_manager.episode.application.response.EpisodeResponse;
import com.movie.manager.movie_manager.episode.application.response.SeasonResponse;

import java.time.LocalDate;
import java.util.List;

public interface SearchEpisodeService {
    SeasonResponse findByTitle(String title, int season);
    List<EpisodeResponse> getEpisodeList(String title, int totalSeasons);
    List<EpisodeResponse> getEpisodesAfterDate(String title, int totalSeasons, LocalDate date);
    List<EpisodeResponse> getEpisodesByRating(String title, int totalSeasons);
    String findSeasonByEpisodeTitle(String episodeTitle, int totalSeasons, String title);
    List<String> getTopSeasons(String title, int totalSeasons);
}
