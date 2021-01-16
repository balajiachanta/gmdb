package com.galvanize.gmdb.service;

import com.galvanize.gmdb.exception.MovieNotFoundException;
import com.galvanize.gmdb.model.Movie;

import java.util.List;

public interface MovieOperations {

     List<Movie> listAllMovies();
     Movie getMovieByTitle(String title) throws MovieNotFoundException;
}
