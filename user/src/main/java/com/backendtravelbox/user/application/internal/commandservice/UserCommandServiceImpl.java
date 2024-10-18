package com.backendtravelbox.user.application.internal.commandservice;

import com.backendtravelbox.user.domain.model.aggregates.User;
import com.backendtravelbox.user.domain.model.commands.CreateUserCommand;
import com.backendtravelbox.user.domain.model.commands.DeleteUserCommand;
import com.backendtravelbox.user.domain.model.commands.UpdateUserCommand;
import com.backendtravelbox.user.domain.service.UserCommandService;
import com.backendtravelbox.user.infraestructure.persistance.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long handle(CreateUserCommand command) {
        if (userRepository.existsByEmail(command.email())){
            throw new IllegalArgumentException("User Already Exists");
        }
        User user = new User(command);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving user" + e.getMessage());
        }
        return user.getId();
    }

    @Override
    public Optional<User> handle (UpdateUserCommand command) {

        if (userRepository.existsByEmailAndIdIsNot(command.email(), command.id())){
            throw new IllegalArgumentException("User with same email already exist");
        }

        var result = userRepository.findById(command.id());
        if (result.isEmpty()){
            throw new IllegalArgumentException("User does not exist");
        }

        var userToUpdate = result.get();
        try {
            var updatedUser = userRepository.save(userToUpdate.updateUser(
                    command.firstName(),
                    command.lastName(),
                    command.email(),
                    command.userName(),
                    command.password(),
                    command.phoneNumber()));
            return Optional.of(updatedUser);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving user" + e.getMessage());
        }
    }

    @Override
    public void handle (DeleteUserCommand command) {

        if(!userRepository.existsById(command.id())){
            throw new IllegalArgumentException("User does not exist");
        }
        try {
            userRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting user" + e.getMessage());
        }
    }
}