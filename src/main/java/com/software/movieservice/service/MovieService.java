package com.software.movieservice.service;

import com.software.movieservice.model.Movie;
import com.software.movieservice.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class MovieService {
    @Autowired
    private MovieRepository repository;

    public Movie create(Movie movie){
//        if(movie==null){
//            throw new RuntimeException("invalid movie");
//        }
        return repository.save(movie);
    }


    public Movie read(Long id){
        System.out.println(id);
        return repository.findById(id).orElseThrow(()->new RuntimeException("Movie not found"));
    }

    public void update(Long id,Movie movie){
        if (movie==null && id==null){
            throw new RuntimeException("Invalid movie");
        }
        if(repository.existsById(id)){
            Movie oldmovieRec=repository.getReferenceById(id);
            oldmovieRec.setActor(movie.getActor());
            oldmovieRec.setName(movie.getName());
            oldmovieRec.setDirector(movie.getDirector());
            oldmovieRec.setId(oldmovieRec.getId());
           repository.save(oldmovieRec);
        }else{
            throw new RuntimeException("movie not found");
        }
    }

    public void delete(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new RuntimeException("Movie not found");
        }
    }
}
