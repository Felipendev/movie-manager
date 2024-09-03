package com.movie.manager.movie_manager.episode.application.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SeasonResponse {

    @JsonAlias("Season")
    private Integer season;

    @JsonAlias("Episodes")
    private List<EpisodeResponse> episodes;

}
