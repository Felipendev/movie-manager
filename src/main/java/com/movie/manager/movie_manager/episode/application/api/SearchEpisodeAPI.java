package com.movie.manager.movie_manager.episode.application.api;


import com.movie.manager.movie_manager.episode.application.response.EpisodeResponse;
import com.movie.manager.movie_manager.episode.application.response.SeasonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/episode")
public interface SearchEpisodeAPI {

    @GetMapping("/{title}")
    @ResponseStatus(HttpStatus.OK)
    SeasonResponse getEpisodes(@PathVariable String title, @RequestParam("season") int season);

    @GetMapping("/all/{title}")
    @ResponseStatus(HttpStatus.OK)
    List<EpisodeResponse> getAllEpisodes(@PathVariable String title, @RequestParam("totalSeasons") int totalSeasons);

    @GetMapping("/episode/after/{title}")
    @ResponseStatus(HttpStatus.OK)
    List<EpisodeResponse> getEpisodesAfterDate(@PathVariable String title, @RequestParam("totalSeasons") int totalSeasons, @RequestParam("date") LocalDate date);

}
