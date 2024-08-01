package com.movie.manager.movie_manager.infra.api;

import com.movie.manager.movie_manager.movie.application.response.MovieResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${omdb.movie.name}", url = "${omdb.movie.url}")
public interface MovieFeignClient {
    @GetMapping
    MovieResponse findByTitle(@RequestParam("apikey") String apiKey, @RequestParam("t") String title);
}
