package com.software.movieservice.service;

import com.software.movieservice.exception.InvalidDataException;
import com.software.movieservice.exception.NotFoundException;
import com.software.movieservice.model.Movie;
import com.software.movieservice.repo.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@Slf4j
public class MovieService {
    @Autowired
    private MovieRepository repository;

    public Movie create(Movie movie) {
        if (movie == null) {
            try {
                throw new InvalidDataException("invalid movie : null");
            } catch (InvalidDataException e) {
                throw new RuntimeException(e);
            }
        }
        return repository.save(movie);
    }


    public Movie read(Long id) {
        System.out.println(id);
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Movie not found") {
        });
    }

    public void update(Long id, Movie movie) {
        if (movie == null && id == null) {
            try {
                throw new InvalidDataException("Invalid movie: null");
            } catch (InvalidDataException e) {
                log.error("catch block");
                throw new RuntimeException(e);
            }
        }
        if (repository.existsById(id)) {
            Movie oldmovieRec = repository.getReferenceById(id);
            oldmovieRec.setActor(movie.getActor());
            oldmovieRec.setName(movie.getName());
            oldmovieRec.setDirector(movie.getDirector());
            oldmovieRec.setId(oldmovieRec.getId());
            repository.save(oldmovieRec);
        } else {
            throw new NotFoundException("movie not found");
        }
    }

    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Movie not found");
        }
    }
}
