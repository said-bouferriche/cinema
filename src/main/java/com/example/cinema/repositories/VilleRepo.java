package com.example.cinema.repositories;

import com.example.cinema.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VilleRepo extends JpaRepository<Ville, Long> {
}
