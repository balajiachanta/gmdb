package com.galvanize.gmdb.service;

import com.galvanize.gmdb.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    public List<Movie> listAllMovies() {

        return  new ArrayList<>();
    }
}
