package com.backendtravelbox.user.interfaces.rest.transform;

import com.backendtravelbox.user.domain.model.commands.UpdateUserCommand;
import com.backendtravelbox.user.interfaces.rest.resource.UpdateUserResource;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(Long id, UpdateUserResource resource) {
        return new UpdateUserCommand(
                id,
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.userName(),
                resource.password(),
                resource.phoneNumber());
    }
}