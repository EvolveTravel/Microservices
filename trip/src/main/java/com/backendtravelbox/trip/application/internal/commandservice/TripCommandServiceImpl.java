package com.backendtravelbox.trip.application.internal.commandservice;

import com.backendtravelbox.trip.domain.model.aggregates.Trip;
import com.backendtravelbox.trip.domain.model.commands.CreateTripCommand;
import com.backendtravelbox.trip.domain.model.commands.DeleteTripCommand;
import com.backendtravelbox.trip.domain.model.commands.UpdateTripCommand;
import com.backendtravelbox.trip.domain.service.TripCommandService;
import com.backendtravelbox.trip.infraestructure.persistance.jpa.repositories.TripRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TripCommandServiceImpl implements TripCommandService {

    public final TripRepository tripRepository;
    public TripCommandServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Long handle (CreateTripCommand command) {
        if(tripRepository.existsByDate(command.date())){
            throw new IllegalArgumentException("Trip already exists");
        }

        Trip trip = new Trip(command);
        try {
            tripRepository.save(trip);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving trip" + e.getMessage());
        }
        return trip.getId();
    }

    @Override
    public Optional<Trip> handle (UpdateTripCommand command) {
        if (tripRepository.existsByDateAndIdIsNot(command.date(), command.id())){
            throw new IllegalArgumentException("Trip with same date already exists");
        }

        var result = tripRepository.findById(command.id());
        if (result.isEmpty()){
            throw new IllegalArgumentException("Trip does not exist");
        }

        var tripToUpdate = result.get();
        try {
            var updatedTrip = tripRepository.save(tripToUpdate.updateTrip(
                    command.origin(),
                    command.destination(),
                    command.date()));
            return Optional.of(updatedTrip);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving trip" + e.getMessage());
        }
    }

    @Override
    public void handle (DeleteTripCommand command) {
        if(!tripRepository.existsById(command.id())){
            throw new IllegalArgumentException("Trip does not exist");
        }
        try {
            tripRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving trip" + e.getMessage());
        }
    }
}