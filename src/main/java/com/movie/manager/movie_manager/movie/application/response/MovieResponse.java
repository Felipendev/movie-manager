package com.movie.manager.movie_manager.movie.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.movie.manager.movie_manager.utils.GenreEnum;
import com.movie.manager.movie_manager.utils.GenreEnumDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class MovieResponse {
    @JsonProperty("Title")
    String title;
    @JsonProperty("Rated")
    String rating;
    @JsonProperty("Genre")
    @JsonDeserialize(using = GenreEnumDeserializer.class)
    List<GenreEnum> genre;
    @JsonProperty("Year")
    String year;
    @JsonProperty("Director")
    String director;
    @JsonProperty("Language")
    String language;
}
