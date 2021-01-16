package com.galvanize.gmdb.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.gmdb.model.Movie;
import com.galvanize.gmdb.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

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
        when(movieService.listAllMovies()).thenReturn(movies);
        mockMvc.perform(get("/gmdb/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Rocketeer"));
    }

    @Test
    public void testGet_listAllMovies_whenReturn204() throws Exception {

        when(movieService.listAllMovies()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/gmdb/movies"))
                .andExpect(status().isNoContent());
                //.andExpect(jsonPath("$[1].title").value("titanic"));

    }


}
