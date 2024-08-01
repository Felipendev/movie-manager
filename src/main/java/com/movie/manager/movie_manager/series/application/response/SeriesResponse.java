package com.movie.manager.movie_manager.series.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.movie.manager.movie_manager.utils.GenreEnum;
import com.movie.manager.movie_manager.utils.GenreEnumDeserializer;
import lombok.Value;

import java.util.List;

@Value
public class SeriesResponse {
    @JsonProperty("Title")
    String title;
    @JsonProperty("TotalSeasons")
    Integer totalSeasons;
    @JsonProperty("Rated")
    String rating;
    @JsonProperty("Plot")
    String plot;
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
