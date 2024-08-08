package com.movie.manager.movie_manager.episode.application.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.movie.manager.movie_manager.episode.domain.Episode;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeResponse(@JsonAlias("Title") String title,
                              @JsonAlias("Episode") Integer episode,
                              @JsonAlias("Season") Integer season,
                              @JsonAlias("imdbRating") String rating,
                              @JsonAlias("Released") String year) {

    public static EpisodeResponse fromEpisode(Episode episode) {
        return new EpisodeResponse(
                episode.getTitle(),
                episode.getEpisodeNumber(),
                episode.getSeason(),
                episode.getFormattedRating(),
                episode.getFormattedYear()
        );
    }
}
