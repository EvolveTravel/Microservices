package com.backendtravelbox.trip.domain.service;

import com.backendtravelbox.trip.domain.model.aggregates.Trip;
import com.backendtravelbox.trip.domain.model.queries.GetAllTripQuery;
import com.backendtravelbox.trip.domain.model.queries.GetTripByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TripQueryService {
    List<Trip> handle (GetAllTripQuery query);
    Optional<Trip> handle (GetTripByIdQuery query);
}