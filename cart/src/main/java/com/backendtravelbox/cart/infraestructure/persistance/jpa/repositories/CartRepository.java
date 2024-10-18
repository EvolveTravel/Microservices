package com.backendtravelbox.cart.infraestructure.persistance.jpa.repositories;

import com.backendtravelbox.cart.domain.model.aggregates.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByProduct(String product);
    Boolean existsByProduct(String product);
    Boolean existsByProductAndIdIsNot(String product, Long id);
}