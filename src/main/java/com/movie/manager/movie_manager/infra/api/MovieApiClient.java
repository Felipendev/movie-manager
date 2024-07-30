package com.movie.manager.movie_manager.infra.api;

import com.movie.manager.movie_manager.movie.application.response.MovieResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieApiClient {
    private final String apiUrl = "http://www.omdbapi.com/?i=tt3896198&apikey=7a4f2c81";

    private MovieResponse getMovieByTitle(String title) {
        RestTemplate template = new RestTemplate();
        String url = apiUrl +  "&t=" + title;
        return template.getForObject(url,MovieResponse.class);
    }
}
