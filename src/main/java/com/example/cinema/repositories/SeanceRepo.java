package com.example.cinema.repositories;

import com.example.cinema.entities.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SeanceRepo extends JpaRepository<Seance, Long> {
}
