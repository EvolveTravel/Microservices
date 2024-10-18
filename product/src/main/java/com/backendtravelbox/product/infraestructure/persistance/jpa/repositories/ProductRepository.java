package com.backendtravelbox.product.infraestructure.persistance.jpa.repositories;

import com.backendtravelbox.product.domain.model.aggregates.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    Boolean existsByName(String name);
    Boolean existsByNameAndIdIsNot(String name, Long id);
}