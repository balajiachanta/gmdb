package com.galvanize.gmdb.service;

import com.galvanize.gmdb.entity.MovieEntity;
import com.galvanize.gmdb.entity.Rating;
import com.galvanize.gmdb.exception.MovieNotFoundException;
import com.galvanize.gmdb.model.Movie;
import com.galvanize.gmdb.repository.MovieRepository;
import com.galvanize.gmdb.repository.RatingRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MovieService implements MovieOperations {

    private MovieRepository movieRepository;

    private RatingRepository ratingRepository;


    public MovieService(MovieRepository movieRepository,RatingRepository ratingRepository) {
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
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

            List<Rating> ratings = ratingRepository.findByMovieId(String.valueOf(returnValue.getId()));
            if(!CollectionUtils.isEmpty(ratings)){
               List<Integer> ratingValue =  ratings.stream().map(ar -> Integer.valueOf(ar.getRating())).collect(Collectors.toList());

                Double average = ratingValue.stream().mapToInt(val -> val).average().orElse(0.0);
                movie.setRating(String.valueOf(average));
            }
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
