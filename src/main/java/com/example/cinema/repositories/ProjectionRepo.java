package com.example.cinema.repositories;

import com.example.cinema.entities.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProjectionRepo extends JpaRepository<Projection, Long> {
}
