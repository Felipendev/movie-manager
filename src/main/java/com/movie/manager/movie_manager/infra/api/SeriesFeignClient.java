package com.movie.manager.movie_manager.infra.api;

import com.movie.manager.movie_manager.serie.application.response.SeriesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${omdb.series.name}", url = "${omdb.series.url}")
public interface SeriesFeignClient {
    @GetMapping
    SeriesResponse findByTitle(@RequestParam("apikey") String apiKey, @RequestParam("t") String title);
}