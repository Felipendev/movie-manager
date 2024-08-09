package com.movie.manager.movie_manager.episode.application.api;


import com.movie.manager.movie_manager.episode.application.response.EpisodeResponse;
import com.movie.manager.movie_manager.episode.application.response.SeasonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(summary = "Fetches all episodes from a specific season of the series.")
    @GetMapping("/{title}/season/{season}")
    @ResponseStatus(HttpStatus.OK)
    SeasonResponse getEpisodes(
            @Parameter(description = "Title of the series") @PathVariable String title,
            @Parameter(description = "Season number") @PathVariable int season);

    @Operation(summary = "Lists all episodes from all seasons of a series.")
    @GetMapping("/{title}/episodes")
    @ResponseStatus(HttpStatus.OK)
    List<EpisodeResponse> getAllEpisodes(
            @Parameter(description = "Title of the series") @PathVariable String title,
            @Parameter(description = "Total number of seasons") @RequestParam("totalSeasons") int totalSeasons);

    @Operation(summary = "Lists all episodes from all seasons that were released after a specific date.")
    @GetMapping("/{title}/episodes/after")
    @ResponseStatus(HttpStatus.OK)
    List<EpisodeResponse> getEpisodesAfterDate(
            @Parameter(description = "Title of the series") @PathVariable String title,
            @Parameter(description = "Total number of seasons") @RequestParam("totalSeasons") int totalSeasons,
            @Parameter(description = "Date for filtering in the format YYYY-MM-DD") @RequestParam("date") LocalDate date);

    @Operation(summary = "Returns the top 5 episodes of the series based on ratings.")
    @GetMapping("/{title}/episodes/top")
    @ResponseStatus(HttpStatus.OK)
    List<EpisodeResponse> getTopFiveEpisodes(
            @Parameter(description = "Title of the series") @PathVariable String title,
            @Parameter(description = "Total number of seasons") @RequestParam("totalSeasons") int totalSeasons);

    @Operation(summary = "Returns the top 3 seasons of the series based on the average ratings of the episodes.")
    @GetMapping("/{title}/top-seasons")
    @ResponseStatus(HttpStatus.OK)
    List<String> getTopSeasons(
            @Parameter(description = "Title of the series") @PathVariable String title,
            @Parameter(description = "Total number of seasons") @RequestParam("totalSeasons") int totalSeasons);

    @Operation(summary = "Finds and returns the season to which a specific episode belongs by the episode title.")
    @GetMapping("/{title}/season-by-episode/{episodeTitle}")
    @ResponseStatus(HttpStatus.OK)
    String getSeasonByTitle(
            @Parameter(description = "Title of the episode") @PathVariable String episodeTitle,
            @Parameter(description = "Total number of seasons") @RequestParam("totalSeasons") int totalSeasons,
            @Parameter(description = "Title of the series") @RequestParam String title);
}
