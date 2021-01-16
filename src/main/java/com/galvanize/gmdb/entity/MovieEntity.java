package com.galvanize.gmdb.entity;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "MOVIE")
public class MovieEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String director;
    private String actors;
    private String release;
    private String description;
    private String rating;

    //@OneToMany(mappedBy = "movieEntity",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //private Set<Rating> ratings;

    public MovieEntity() {
    }

    public MovieEntity(String title, String director, String actors, String release, String description, String rating) {
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.release = release;
        this.description = description;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

//    public Set<Rating> getRatings() {
//        return ratings;
//    }
//
//    public void setRatings(Set<Rating> ratings) {
//        this.ratings = ratings;
//    }
}
