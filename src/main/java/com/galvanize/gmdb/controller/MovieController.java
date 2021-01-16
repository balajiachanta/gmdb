package com.galvanize.gmdb.controller;

import com.galvanize.gmdb.model.Movie;
import com.galvanize.gmdb.service.MovieOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    private MovieOperations movieService;

    public MovieController(MovieOperations movieService) {
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

    @GetMapping("/gmdb/movie/{title}")
    public Movie getMovieByTitle(@PathVariable String title){

        return movieService.getMovieByTitle(title);
    }
}
