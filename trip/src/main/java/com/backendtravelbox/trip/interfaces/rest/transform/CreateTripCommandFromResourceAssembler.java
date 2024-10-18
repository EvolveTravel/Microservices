package com.backendtravelbox.trip.interfaces.rest.transform;

import com.backendtravelbox.trip.domain.model.commands.CreateTripCommand;
import com.backendtravelbox.trip.interfaces.rest.resource.CreateTripResource;

public class CreateTripCommandFromResourceAssembler {
    public static CreateTripCommand toCommandFromResource(CreateTripResource resource) {
        return new CreateTripCommand(
                resource.origin(),
                resource.destination(),
                resource.date());
    }
}
