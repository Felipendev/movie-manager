package com.movie.manager.movie_manager.serie.application.api;

import com.movie.manager.movie_manager.serie.application.response.SeriesResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/series")
@RestController
public interface SearchSeriesAPI {
    @GetMapping("/{title}")
    SeriesResponse getSeries(@PathVariable String title);
}
