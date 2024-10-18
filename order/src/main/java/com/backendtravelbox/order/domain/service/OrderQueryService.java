package com.backendtravelbox.order.domain.service;

import com.backendtravelbox.order.domain.model.aggregates.Order;
import com.backendtravelbox.order.domain.model.queries.GetAllOrderQuery;
import com.backendtravelbox.order.domain.model.queries.GetOrderByIdQuery;

import java.util.List;
import java.util.Optional;

public interface OrderQueryService {
    List<Order> handle (GetAllOrderQuery query);
    Optional<Order> handle (GetOrderByIdQuery query);
}