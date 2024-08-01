package com.movie.manager.movie_manager.series.application.service;

import com.movie.manager.movie_manager.series.application.response.SeriesResponse;

public interface SearchSeriesService {
    SeriesResponse findSerieByTitle(String title);
}
