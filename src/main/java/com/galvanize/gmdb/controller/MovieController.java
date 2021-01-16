package com.galvanize.gmdb.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.gmdb.model.Movie;
import com.galvanize.gmdb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
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

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/gmdb/movies")
    public ResponseEntity<List<Movie>> listAllMovies(){
        List<Movie> movies = movieService.listAllMovies();
        ResponseEntity<List<Movie>> responseEntity = null;
        if(!CollectionUtils.isEmpty(movies)){
            return new ResponseEntity<List<Movie>>(movies,HttpStatus.OK);

        }else{
            return  new ResponseEntity<List<Movie>>(movies ,HttpStatus.NO_CONTENT);
        }


    }
}
