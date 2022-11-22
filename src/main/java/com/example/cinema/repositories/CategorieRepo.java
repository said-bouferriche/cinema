package com.example.cinema.repositories;

import com.example.cinema.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategorieRepo extends JpaRepository<Categorie, Long> {
}
