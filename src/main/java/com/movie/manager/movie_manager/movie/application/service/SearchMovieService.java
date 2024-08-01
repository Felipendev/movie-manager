package com.movie.manager.movie_manager.movie.application.service;

import com.movie.manager.movie_manager.movie.application.response.MovieResponse;

public interface SearchMovieService {
    MovieResponse findMovieByTitle(String title);
}
