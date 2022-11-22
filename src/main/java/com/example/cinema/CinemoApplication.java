package com.example.cinema;

import com.example.cinema.services.ICinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemoApplication implements CommandLineRunner {

    @Autowired
    private ICinemaService iCinemaService;

    //public CinemoApplication(ICinemaService iCinemaService) {
    //    this.iCinemaService = iCinemaService;
    //}

    public static void main(String[] args) {
        SpringApplication.run(CinemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        iCinemaService.initVilles();
        iCinemaService.initCinemas();
        iCinemaService.initSalles();
        iCinemaService.initPlaces();
        iCinemaService.initSeances();
        iCinemaService.initCategories();
        iCinemaService.initFilms();
        iCinemaService.initProjections();
        iCinemaService.initTickets();
    }
}
