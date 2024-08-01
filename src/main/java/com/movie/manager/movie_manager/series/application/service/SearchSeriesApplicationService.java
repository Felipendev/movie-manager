package com.movie.manager.movie_manager.series.application.service;

import com.movie.manager.movie_manager.infra.api.SeriesFeignClient;
import com.movie.manager.movie_manager.series.application.response.SeriesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class SearchSeriesApplicationService implements SearchSeriesService {
    private final SeriesFeignClient seriesFeignClient;

    @Value("${omdb.series.apikey}")
    private String API_KEY;

    @Override
    public SeriesResponse findSerieByTitle(String title) {
        log.info("[start] SearchSeriesApplicationService - findSerieByTitle");
        SeriesResponse series = seriesFeignClient.findByTitle(API_KEY, title);
        log.info("[finish] SearchSeriesApplicationService - findSerieByTitle");
        return series;
    }
}
