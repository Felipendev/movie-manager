package com.movie.manager.movie_manager.series.application.api;

import com.movie.manager.movie_manager.series.application.response.SeriesResponse;
import com.movie.manager.movie_manager.series.application.service.SearchSeriesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class SearchSeriesRestController implements SearchSeriesAPI {
    private final SearchSeriesService searchSeriesService;

    @Override
    public SeriesResponse getSeries(String title) {
        log.info("[start] SearchSeriesRestController - getSeries");
        SeriesResponse series = searchSeriesService.findSerieByTitle(title);
        log.info("[finish] SearchSeriesRestController - getSeries");
        return series;
    }
}
