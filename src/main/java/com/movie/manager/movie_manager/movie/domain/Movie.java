package com.movie.manager.movie_manager.movie.domain;

import com.movie.manager.movie_manager.utils.GenreEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Movie {
    private String title;
    private String rating;
    @Enumerated(EnumType.STRING)
    private GenreEnum genre;
    private LocalDate year;
    private String director;
    private String language;
}
