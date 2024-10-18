package com.backendtravelbox.user.domain.service;

import com.backendtravelbox.user.domain.model.aggregates.User;
import com.backendtravelbox.user.domain.model.queries.GetAllUserQuery;
import com.backendtravelbox.user.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUserQuery query);
    Optional<User> handle(GetUserByIdQuery query);
}