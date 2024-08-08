package com.movie.manager.movie_manager.episode.application.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonResponse(@JsonAlias("Season") Integer numero,
                             @JsonAlias("Episodes") List<EpisodeResponse> episodios) {
}
