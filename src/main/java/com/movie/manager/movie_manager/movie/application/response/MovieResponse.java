package com.movie.manager.movie_manager.movie.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.movie.manager.movie_manager.utils.GenreEnum;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MovieResponse {
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Rated")
    private String rating;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Language")
    private String language;
}
