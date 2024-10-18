package com.backendtravelbox.order.domain.service;

import com.backendtravelbox.order.domain.model.aggregates.Order;
import com.backendtravelbox.order.domain.model.commands.CreateOrderCommand;
import com.backendtravelbox.order.domain.model.commands.DeleteOrderCommand;
import com.backendtravelbox.order.domain.model.commands.UpdateOrderCommand;

import java.util.Optional;

public interface OrderCommandService {
    Long handle (CreateOrderCommand command);
    Optional<Order> handle (UpdateOrderCommand command);
    void handle (DeleteOrderCommand command);
}