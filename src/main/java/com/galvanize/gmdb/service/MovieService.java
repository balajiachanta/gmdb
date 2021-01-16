package com.galvanize.gmdb.service;

import com.galvanize.gmdb.entity.MovieEntity;
import com.galvanize.gmdb.exception.MovieNotFoundException;
import com.galvanize.gmdb.model.Movie;
import com.galvanize.gmdb.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService implements MovieOperations {

    private MovieRepository movieRepository;


    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> listAllMovies() {
        List<MovieEntity> movieEntities = movieRepository.findAll();
        List<Movie> movies = movieEntities.stream().map(m -> new Movie(m.getTitle(), m.getDirector(), m.getActors(),
                                                                      m.getRelease(), m.getDescription(), m.getRating()))
                                                    .collect(Collectors.toList());
        return movies;
    }

    @Override
    public Movie getMovieByTitle(String title) throws MovieNotFoundException {

        Optional<MovieEntity> movieEntity =  movieRepository.findByTitle(title);
        Movie movie = null;
        if(movieEntity.isPresent()){
            MovieEntity returnValue = movieEntity.get();
             movie = new Movie(returnValue.getTitle(), returnValue.getDirector(), returnValue.getActors(),
                    returnValue.getRelease(), returnValue.getDescription(), returnValue.getRating());
        }else{
            //return some exception
            throw new MovieNotFoundException(title+" Not Found");
        }
        return movie;

    }


    @Override
    public Movie updateMovieByTitle(String rating,String title) throws MovieNotFoundException {

        Optional<MovieEntity> movieEntity =  movieRepository.findByTitle(title);
        Movie movie = null;
        if(movieEntity.isPresent()){
            MovieEntity updateValue = movieEntity.get();
            updateValue.setRating(rating);
            movieRepository.save(updateValue);
        }else{
            //return some exception
            throw new MovieNotFoundException(title+" Not Found");
        }
        return this.getMovieByTitle(title);
    }


}
