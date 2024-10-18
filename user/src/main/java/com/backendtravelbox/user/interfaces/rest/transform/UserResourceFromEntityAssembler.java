package com.backendtravelbox.user.interfaces.rest.transform;

import com.backendtravelbox.user.domain.model.aggregates.User;
import com.backendtravelbox.user.interfaces.rest.resource.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity){
        return new UserResource(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getUserName(),
                entity.getPassword(),
                entity.getPhoneNumber());
    }
}