package com.galvanize.gmdb.service;

import com.galvanize.gmdb.entity.MovieEntity;
import com.galvanize.gmdb.model.Movie;
import com.galvanize.gmdb.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private MovieRepository movieRepository;


    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> listAllMovies() {

        List<MovieEntity> movieEntities = movieRepository.findAll();

        List<Movie> movies = movieEntities.stream().map(m -> new Movie(m.getTitle(), m.getDirector(), m.getActors(),
                                                                      m.getRelease(), m.getDescription(), m.getRating()))
                                                    .collect(Collectors.toList());

        return movies;
    }
}
