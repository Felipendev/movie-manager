package com.movie.manager.movie_manager.episode.domain;

import com.movie.manager.movie_manager.episode.application.response.EpisodeResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Episode {
    private Integer season;
    private String title;
    private Integer episodeNumber;
    private Double rating;
    private LocalDate yeah;

        public Episode(Integer seasonNumber, EpisodeResponse episode) {
            this.season = seasonNumber;
            this.title = episode.title();
            this.episodeNumber = episode.episode();

        try {
            this.rating = Double.valueOf(episode.rating());
        } catch (NumberFormatException ex) {
            this.rating = 0.0;
        }

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                this.yeah = LocalDate.parse(episode.year(), formatter);
            } catch (DateTimeParseException ex) {
                this.yeah = null;
            }
        }

    public String getFormattedRating() {
        return this.rating != null ? this.rating.toString() : "N/A";
    }
    public String getFormattedYear() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
        return this.yeah != null ? this.yeah.format(formatter) : "N/A";
    }
}
