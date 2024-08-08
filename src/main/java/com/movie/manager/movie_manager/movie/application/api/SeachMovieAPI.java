package com.movie.manager.movie_manager.movie.application.api;

import com.movie.manager.movie_manager.movie.application.response.MovieResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public interface SeachMovieAPI {
    @GetMapping("/{title}")
    @ResponseStatus(HttpStatus.OK)
    MovieResponse getMovieByTitle(@PathVariable String title);
}
