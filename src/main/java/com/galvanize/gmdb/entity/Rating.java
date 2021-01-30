package com.galvanize.gmdb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rating {

    @Id
    @GeneratedValue
    private Long id;

    private String rating;
    private String ratedBy;
    private String movieId;


    public Rating() {
    }

    public Rating(String rating, String ratedBy) {
        this.rating = rating;
        this.ratedBy = ratedBy;
    }

    public Rating(String rating, String ratedBy, String movieId) {
        this.rating = rating;
        this.ratedBy = ratedBy;
        this.movieId = movieId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(String ratedBy) {
        this.ratedBy = ratedBy;
    }

//    public MovieEntity getMovieEntity() {
//        return movieEntity;
//    }
//
//    public void setMovieEntity(MovieEntity movieEntity) {
//        this.movieEntity = movieEntity;
//    }
}
