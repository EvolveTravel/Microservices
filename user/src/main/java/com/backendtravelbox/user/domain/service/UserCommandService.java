package com.backendtravelbox.user.domain.service;

import com.backendtravelbox.user.domain.model.aggregates.User;
import com.backendtravelbox.user.domain.model.commands.CreateUserCommand;
import com.backendtravelbox.user.domain.model.commands.DeleteUserCommand;
import com.backendtravelbox.user.domain.model.commands.UpdateUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Long handle (CreateUserCommand command);
    Optional<User> handle (UpdateUserCommand command);
    void handle (DeleteUserCommand command);
}