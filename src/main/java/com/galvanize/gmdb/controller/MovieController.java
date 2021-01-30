package com.galvanize.gmdb.controller;

import com.galvanize.gmdb.bean.UpdateBean;
import com.galvanize.gmdb.exception.MovieNotFoundException;
import com.galvanize.gmdb.model.Movie;
import com.galvanize.gmdb.service.MovieOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
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
            log.info("Movies exist");
            return new ResponseEntity<List<Movie>>(movies,HttpStatus.OK);

        }else{

            return  new ResponseEntity<List<Movie>>(movies ,HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/gmdb/movie/{title}")
    public Movie getMovieByTitle(@PathVariable String title) throws MovieNotFoundException {
        return movieService.getMovieByTitle(title);
    }

    @PutMapping("/gmdb/movie")
    public Movie updateMovieRatingByTitle(@RequestBody UpdateBean updateBean) throws MovieNotFoundException {
        return movieService.updateMovieByTitle(updateBean.getRating(), updateBean.getTitle());
    }
}
