package com.galvanize.gmdb.repository;

import com.galvanize.gmdb.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity,Long> {

    Optional<MovieEntity> findByTitle(String title);
}
