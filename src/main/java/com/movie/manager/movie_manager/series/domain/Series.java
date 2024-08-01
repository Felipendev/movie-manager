package com.movie.manager.movie_manager.series.domain;

import com.movie.manager.movie_manager.utils.GenreEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Series {
    private String title;
    private Integer totalSeasons;
    private String rating;
    private String plot;
    @Enumerated(EnumType.STRING)
    private List<GenreEnum> genre;
    private LocalDate year;
    private String director;
    private String language;
}
