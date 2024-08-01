package com.movie.manager.movie_manager.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GenreEnumDeserializer extends JsonDeserializer<List<GenreEnum>> {
    @Override
    public List<GenreEnum> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String genreString = jsonParser.getText();
        return Arrays.stream(genreString.split(", "))
                .map(String::toUpperCase)
                .map(GenreEnum::valueOf)
                .collect(Collectors.toList());
    }
}
