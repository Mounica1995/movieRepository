package com.software.movieservice.model;

import lombok.Data;


import javax.persistence.*;
import java.util.*;
@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String director;
//    @ElementCollection
//    private List<String> actors=new ArrayList<>();
    private String actor;
}
