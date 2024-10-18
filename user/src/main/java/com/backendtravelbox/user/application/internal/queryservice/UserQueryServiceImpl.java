package com.backendtravelbox.user.application.internal.queryservice;

import com.backendtravelbox.user.domain.model.aggregates.User;
import com.backendtravelbox.user.domain.model.queries.GetAllUserQuery;
import com.backendtravelbox.user.domain.model.queries.GetUserByIdQuery;
import com.backendtravelbox.user.domain.service.UserQueryService;
import com.backendtravelbox.user.infraestructure.persistance.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.id());
    }

    @Override
    public List<User> handle(GetAllUserQuery query) {
        return userRepository.findAll();
    }
}