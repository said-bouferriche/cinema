package com.example.cinema.repositories;

import com.example.cinema.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FilmRepo extends JpaRepository<Film, Long> {
}
