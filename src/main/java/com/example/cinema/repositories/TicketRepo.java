package com.example.cinema.repositories;

import com.example.cinema.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TicketRepo extends JpaRepository<Ticket, Long> {
}
