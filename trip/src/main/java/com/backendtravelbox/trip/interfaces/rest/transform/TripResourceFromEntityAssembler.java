package com.backendtravelbox.trip.interfaces.rest.transform;

import com.backendtravelbox.trip.domain.model.aggregates.Trip;
import com.backendtravelbox.trip.interfaces.rest.resource.TripResource;

public class TripResourceFromEntityAssembler {
    public static TripResource toResourceFromEntity(Trip entity) {
        return new TripResource(
                entity.getId(),
                entity.getOrigin(),
                entity.getDestination(),
                entity.getDate());
    }
}
