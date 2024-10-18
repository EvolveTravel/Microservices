package com.backendtravelbox.trip.interfaces.rest.transform;

import com.backendtravelbox.trip.domain.model.commands.UpdateTripCommand;
import com.backendtravelbox.trip.interfaces.rest.resource.UpdateTripResource;

public class UpdateTripCommandFromResourceAssembler {
    public static UpdateTripCommand toCommandFromResource(Long id, UpdateTripResource resource) {
        return new UpdateTripCommand(
                id,
                resource.origin(),
                resource.destination(),
                resource.date());
    }
}
