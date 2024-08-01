package com.movie.manager.movie_manager.movie.application.api;


import com.movie.manager.movie_manager.movie.application.response.MovieResponse;
import com.movie.manager.movie_manager.movie.application.service.SearchMovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class SearchMovieRestController implements SeachMovieAPI {

    private final SearchMovieService searchMovieService;

    @Override
    public MovieResponse getMovieByTitle(@PathVariable String title) {
        log.info("[start] - SearchMovieAPI - getMovieByTitle");
        log.info("[title] {} ", title);
        MovieResponse movie = searchMovieService.findMovieByTitle(title);                ;
        log.info("[finish] - SearchMovieAPI - getMovieByTitle");
        return movie;
    }
}
