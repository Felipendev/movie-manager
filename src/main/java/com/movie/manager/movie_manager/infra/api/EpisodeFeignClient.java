package com.movie.manager.movie_manager.infra.api;

import com.movie.manager.movie_manager.episode.application.response.SeasonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${omdb.episode.name}", url = "${omdb.episode.url}")
public interface EpisodeFeignClient {
    @GetMapping
    SeasonResponse findByTitle(@RequestParam("apikey") String apiKey, @RequestParam("t") String title, @RequestParam("season") int season);
}

