package com.example.cinema.repositories;

import com.example.cinema.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SalleRepo extends JpaRepository<Salle, Long> {
}
