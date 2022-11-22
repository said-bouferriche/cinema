package com.example.cinema.web;


import com.example.cinema.entities.Film;
import com.example.cinema.entities.Ticket;
import com.example.cinema.repositories.FilmRepo;
import com.example.cinema.repositories.TicketRepo;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaRestController {
    //@Autowired
    private FilmRepo filmRepo;
    //@Autowired
    private TicketRepo ticketRepo;

    public CinemaRestController(FilmRepo filmRepo, TicketRepo ticketRepo) {
        this.filmRepo = filmRepo;
        this.ticketRepo = ticketRepo;
    }

    @GetMapping(path = "/listfilms/{id}", produces =MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable("id") Long id) throws IOException {
        Film film = filmRepo.findById(id).get();
        String pictureName = film.getPhoto();
        File file = new File(System.getProperty("user.home")+"/cinema/"+pictureName+".jpg");
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    @PostMapping("/payerTickets")
    @Transactional
    public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){
        List<Ticket> reservedTickets = new ArrayList<>();
        ticketForm.getTickets().forEach(id->{
            System.out.println(id);

            Ticket ticket = ticketRepo.findById(id).get();
            ticket.setNomClient(ticketForm.getNameClient());
            ticket.setReserve(true);
            ticket.setCodePayement(ticketForm.getCodePayement());
            ticketRepo.save(ticket);
            reservedTickets.add(ticket);
        });
        return reservedTickets;
    }
}

@Data
class TicketForm {
    private String nameClient;
    private int codePayement;
    private List<Long> tickets = new ArrayList<>();
}
