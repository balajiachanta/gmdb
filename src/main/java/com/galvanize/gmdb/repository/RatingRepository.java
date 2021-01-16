package com.galvanize.gmdb.repository;

import com.galvanize.gmdb.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {

    List<Rating> findByMovieId(String movieId);
}
