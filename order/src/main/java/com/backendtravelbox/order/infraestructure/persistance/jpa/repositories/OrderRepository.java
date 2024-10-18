package com.backendtravelbox.order.infraestructure.persistance.jpa.repositories;

import com.backendtravelbox.order.domain.model.aggregates.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderNumber(Double orderNumber);
    Boolean existsByOrderNumber(Double orderNumber);
    Boolean existsByOrderNumberAndIdIsNot(Double orderNumber, Long id);
}