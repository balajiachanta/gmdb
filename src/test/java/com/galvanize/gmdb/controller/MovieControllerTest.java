package com.galvanize.gmdb.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.gmdb.entity.MovieEntity;
import com.galvanize.gmdb.model.Movie;
import com.galvanize.gmdb.repository.MovieRepository;
import com.galvanize.gmdb.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@AutoConfigureTestDatabase
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    void init(){
        movieRepository.deleteAll();
    }

    List<Movie> movies;

    @Autowired
    private ObjectMapper mapper;

    public void loadMovies() throws IOException {
        movies = new ArrayList<>();
        File heroFilePath = new File("src/main/resources/data/moviedata.json");
        movies = mapper.readValue(heroFilePath, new TypeReference<List<Movie>>() {});

    }


    @Test
    public void testGet_listAllMovies_whenReturn200() throws Exception {
        loadMovies();
        List<MovieEntity> moviesList = movies.stream().map(m -> new MovieEntity(m.getTitle(), m.getDirector(), m.getActors(),
                m.getRelease(), m.getDescription(), m.getRating()))
                .collect(Collectors.toList());

        movieRepository.save(moviesList.get(0));

        mockMvc.perform(get("/gmdb/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Rocketeer"));
    }

    @Test
    public void testGet_listAllMovies_whenReturn204() throws Exception {
        mockMvc.perform(get("/gmdb/movies"))
                .andExpect(status().isNoContent());

    }

    @Test
    public void testGet_getMovie_ByTitle() throws Exception {
        loadMovies();
        List<MovieEntity> moviesList = movies.stream().map(m -> new MovieEntity(m.getTitle(), m.getDirector(), m.getActors(),
                m.getRelease(), m.getDescription(), m.getRating()))
                .collect(Collectors.toList());

        movieRepository.save(moviesList.get(0));

        mockMvc.perform(get("/gmdb/movie/Rocketeer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Rocketeer"))
                .andExpect(jsonPath("$.director").value("Jay Light"))
                .andExpect(jsonPath("$.actors").value("Christopher Coakley"))
                .andExpect(jsonPath("$.release").value("2012"))
                .andExpect(jsonPath("$.description").value("great movie"))
                .andExpect(jsonPath("$.rating").value("6"));

    }


}
