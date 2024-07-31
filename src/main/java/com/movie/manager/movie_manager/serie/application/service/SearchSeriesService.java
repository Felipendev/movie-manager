package com.movie.manager.movie_manager.serie.application.service;

import com.movie.manager.movie_manager.serie.application.response.SeriesResponse;

public interface SearchSeriesService {
    SeriesResponse findSerieByTitle(String title);
}
