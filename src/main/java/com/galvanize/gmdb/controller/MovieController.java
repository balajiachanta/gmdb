package com.galvanize.gmdb.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.gmdb.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class MovieController {

    List<Movie> movies;

    @Autowired
    private ObjectMapper mapper;

    @PostConstruct
    public void init() throws IOException {
        movies = new ArrayList<>();
        File heroFilePath = new File("src/main/resources/data/moviedata.json");
        movies = mapper.readValue(heroFilePath, new TypeReference<List<Movie>>() {});

    }

    @GetMapping("/gmdb/movies")
    public List<Movie> listAllMovies(){

        Movie movie = new Movie();
        movie.setTitle("titanic");
        movies.add(movie);

        return movies;

    }
}
