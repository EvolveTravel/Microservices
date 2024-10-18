package com.backendtravelbox.trip.application.internal.queryservice;

import com.backendtravelbox.trip.domain.model.aggregates.Trip;
import com.backendtravelbox.trip.domain.model.queries.GetAllTripQuery;
import com.backendtravelbox.trip.domain.model.queries.GetTripByIdQuery;
import com.backendtravelbox.trip.domain.service.TripQueryService;
import com.backendtravelbox.trip.infraestructure.persistance.jpa.repositories.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripQueryServiceImpl implements TripQueryService {

    private final TripRepository tripRepository;

    public TripQueryServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Optional<Trip> handle (GetTripByIdQuery query) {
        return tripRepository.findById(query.id());
    }

    @Override
    public List<Trip> handle (GetAllTripQuery query) {
        return tripRepository.findAll();
    }
}