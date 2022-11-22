package com.example.cinema.web;


import com.example.cinema.entities.Film;
import com.example.cinema.repositories.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CinemaRestController {
    @Autowired
    private FilmRepo filmRepo;

    @GetMapping("/listfilms")
    public List<Film> films(){
        return filmRepo.findAll();
    }
}
