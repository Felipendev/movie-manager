package com.movie.manager.movie_manager.movie.application.service;

import com.movie.manager.movie_manager.infra.api.MovieApiClient;
import com.movie.manager.movie_manager.movie.application.response.MovieResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class SearchMovieApplicationService implements SearchMovieService {

    private final MovieApiClient movieApiClient;

    @Value("${omdb.apikey}")
    private String apikey;

    @Override
    public MovieResponse findMovieByTitle(String title) {
        log.info("[start] SearchMovieApplicationService - findMovieByTitle");
        MovieResponse movie = movieApiClient.getMovieByTitle(apikey, title);
        log.info("[finish] SearchMovieApplicationService - findMovieByTitle");
        return movie;
    }
}
