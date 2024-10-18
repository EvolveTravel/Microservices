package com.backendtravelbox.trip.domain.service;

import com.backendtravelbox.trip.domain.model.aggregates.Trip;
import com.backendtravelbox.trip.domain.model.commands.CreateTripCommand;
import com.backendtravelbox.trip.domain.model.commands.DeleteTripCommand;
import com.backendtravelbox.trip.domain.model.commands.UpdateTripCommand;

import java.util.Optional;

public interface TripCommandService {
    Long handle (CreateTripCommand command);
    Optional<Trip> handle(UpdateTripCommand command);
    void handle (DeleteTripCommand command);
}