package com.movie.manager.movie_manager.movie.application.api;


import com.movie.manager.movie_manager.movie.application.response.MovieResponse;
import com.movie.manager.movie_manager.movie.application.service.SearchMovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
@Log4j2
public class SearchMovieAPI {

    private final SearchMovieService searchMovieService;

    @GetMapping("/{title}")
    public MovieResponse getMovieByTitle(@PathVariable String title) {
        log.info("[start] - SearchMovieAPI - getMovieByTitle");
        log.info("[title] {} ", title);
        MovieResponse movie = searchMovieService.findMovieByTitle(title);                ;
        log.info("[finish] - SearchMovieAPI - getMovieByTitle");
        return movie;
    }
}
