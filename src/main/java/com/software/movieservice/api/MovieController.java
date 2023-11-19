package com.software.movieservice.api;

import com.software.movieservice.model.Movie;
import com.software.movieservice.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        log.info("getting========");
        Movie movie = movieService.read(id);
        return ResponseEntity.ok(movie);
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        System.out.println("=====movie=========" + movie);
        Movie createmovie = movieService.create(movie);
        System.out.println("==============" + createmovie);
        return ResponseEntity.ok(createmovie);
    }

    @PutMapping("/{id}")
    public void updateMovie(@RequestBody(required = false) Movie movie, @PathVariable Long id) {
        movieService.update(id, movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        log.info("deleting record==========");
        movieService.delete(id);
    }
}
