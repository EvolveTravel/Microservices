package com.backendtravelbox.user.infraestructure.persistance.jpa.repositories;

import com.backendtravelbox.user.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByEmailAndIdIsNot(String email, Long id);
}