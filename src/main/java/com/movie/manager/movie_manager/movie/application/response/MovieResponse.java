package com.movie.manager.movie_manager.movie.application.response;

import com.movie.manager.movie_manager.utils.GenreEnum;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MovieResponse {
    private String title;
    private String rating;
    private GenreEnum genre;
    private LocalDate year;
    private String director;
    private String language;
}
