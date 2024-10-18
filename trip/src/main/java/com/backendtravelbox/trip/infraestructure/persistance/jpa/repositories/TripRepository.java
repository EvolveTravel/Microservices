package com.backendtravelbox.trip.infraestructure.persistance.jpa.repositories;

import com.backendtravelbox.trip.domain.model.aggregates.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {
    Optional<Trip> findByDate(String date);
    Boolean existsByDate(String date);
    Boolean existsByDateAndIdIsNot(String date, Long id);
}