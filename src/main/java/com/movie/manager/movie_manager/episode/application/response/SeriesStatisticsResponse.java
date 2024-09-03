package com.movie.manager.movie_manager.episode.application.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SeriesStatisticsResponse {
    private final String seriesTitle;
    private final int highestRatedEpisode;
    private final int lowestRatedEpisode;
    private final double averageRating;
    private final long totalRatedEpisodes;
}

