package com.movie.manager.movie_manager.serie.domain;

import com.movie.manager.movie_manager.utils.GenreEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", name = "idIntegrationLog", updatable = false, unique = true, nullable = false)
    @EqualsAndHashCode.Exclude
    private UUID id;
    private String title;
    private Integer totalSeasons;
    private String rating;
    private String plot;
    @Enumerated(EnumType.STRING)
    private GenreEnum genre;
    private LocalDate year;
    private String director;
    private String language;
}
