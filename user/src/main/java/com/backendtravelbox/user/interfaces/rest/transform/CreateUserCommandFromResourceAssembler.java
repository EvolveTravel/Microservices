package com.backendtravelbox.user.interfaces.rest.transform;

import com.backendtravelbox.user.domain.model.commands.CreateUserCommand;
import com.backendtravelbox.user.interfaces.rest.resource.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.userName(),
                resource.password(),
                resource.phoneNumber());
    }
}