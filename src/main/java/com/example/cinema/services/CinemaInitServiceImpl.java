package com.example.cinema.services;


import com.example.cinema.entities.*;
import com.example.cinema.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaService{

    private VilleRepo villeRepo;
    private CinemaRepo cinemaRepo;
    private CategorieRepo categorieRepo;
    private PlaceRepo placeRepo;
    private ProjectionRepo projectionRepo;
    private SalleRepo salleRepo;
    private SeanceRepo seanceRepo;
    private TicketRepo ticketRepo;
    private FilmRepo filmRepo;


    public CinemaInitServiceImpl(VilleRepo villeRepo, CinemaRepo cinemaRepo, CategorieRepo categorieRepo, PlaceRepo placeRepo, ProjectionRepo projectionRepo, SalleRepo salleRepo, SeanceRepo seanceRepo, TicketRepo ticketRepo, FilmRepo filmRepo) {
        this.villeRepo = villeRepo;
        this.cinemaRepo = cinemaRepo;
        this.categorieRepo = categorieRepo;
        this.placeRepo = placeRepo;
        this.projectionRepo = projectionRepo;
        this.salleRepo = salleRepo;
        this.seanceRepo = seanceRepo;
        this.ticketRepo = ticketRepo;
        this.filmRepo = filmRepo;
    }


    @Override
    public void initVilles() {
        Stream.of("Casablanca", "Marrakech", "Rabat", "Tanger").forEach(nameVille->{
            Ville ville = new Ville();
            ville.setName(nameVille);
            villeRepo.save(ville);

        });
    }

    @Override
    public void initCinemas() {
        villeRepo.findAll().forEach(ville -> {
            Stream.of("MegaRama", "IMAX", "FOUNOUN", "CHAHRAZAD", "DAOULIZ")
                .forEach(nameCinema->{
                    Cinema cinema = new Cinema();
                    cinema.setName(nameCinema);
                    cinema.setNombreSalles(3+(int)(Math.random()*7));
                    cinema.setVille(ville);
                    cinemaRepo.save(cinema);
                });
        });
    }

    @Override
    public void initSalles() {
        cinemaRepo.findAll().forEach(cinema -> {
            for (int i = 0; i<cinema.getNombreSalles(); i++){
                Salle salle = new Salle();
                salle.setName("Salle" + (i+1));
                salle.setCinema(cinema);
                salle.setNombrePlace(30 + (int) (Math.random()*9));
                salleRepo.save(salle);
            }
        });

    }

    @Override
    public void initSeances() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("12:00", "15:00", "17:00", "19:00", "21:00").forEach(s ->{
            Seance seance = new Seance();
            try {
                seance.setHeureDebut(dateFormat.parse(s));
                seanceRepo.save(seance);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

    }

    @Override
    public void initPlaces() {
        salleRepo.findAll().forEach(salle -> {
            for (int i=0;i<salle.getNombrePlace();i++){
                Place place = new Place();
                place.setNumero(i+1);
                place.setSalle(salle);
                placeRepo.save(place);
            }
        });

    }

    @Override
    public void initFilms() {
        double[] durees = new double[]{1,1.5,2,2.5,3};
        List<Categorie> categories = categorieRepo.findAll();
        Stream.of("Game of thrones", "Iron Man", "Spider man", "Last Kingdom", "vikings").forEach(f->{
            Film film = new Film();
            film.setTitre(f);
            film.setDuree(durees[new Random().nextInt(durees.length)]);
            film.setPhoto(f.toLowerCase().replaceAll(" ", ""));
            film.setCategorie(categories.get(new Random().nextInt(categories.size())));
            filmRepo.save(film);
        });

    }

    @Override
    public void initProjections() {
        double[] prix = new double[]{30,50,60,70,90,100};
        villeRepo.findAll().forEach(ville -> {
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle -> {
                    filmRepo.findAll().forEach(film -> {
                        seanceRepo.findAll().forEach(seance -> {
                            Projection projection = new Projection();
                            projection.setDateProjection(new Date());
                            projection.setFilm(film);
                            projection.setPrix(prix[new Random().nextInt(prix.length)]);
                            projection.setSalle(salle);
                            projection.setSeances(seance);
                            projectionRepo.save(projection);

                        });
                    });
                });
            });
        });

    }

    @Override
    public void initTickets() {
        projectionRepo.findAll().forEach(p->{
            p.getSalle().getPlaces().forEach(place -> {
                Ticket ticket = new Ticket();
                ticket.setPlace(place);
                ticket.setPrix(p.getPrix());
                ticket.setProjection(p);
                ticket.setReserve(false);
                ticketRepo.save(ticket);
            });
        });

    }

    @Override
    public void initCategories() {
        Stream.of("Histoire", "Actions", "Fiction", "Drama").forEach(c ->{
            Categorie categorie = new Categorie();
            categorie.setName(c);
            categorieRepo.save(categorie);
        });

    }
}
