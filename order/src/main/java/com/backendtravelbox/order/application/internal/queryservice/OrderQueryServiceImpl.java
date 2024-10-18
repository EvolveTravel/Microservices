package com.backendtravelbox.order.application.internal.queryservice;

import com.backendtravelbox.order.domain.model.aggregates.Order;
import com.backendtravelbox.order.domain.model.queries.GetAllOrderQuery;
import com.backendtravelbox.order.domain.model.queries.GetOrderByIdQuery;
import com.backendtravelbox.order.domain.service.OrderQueryService;
import com.backendtravelbox.order.infraestructure.persistance.jpa.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {

    private final OrderRepository orderRepository;

    public OrderQueryServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> handle (GetOrderByIdQuery query){
        return orderRepository.findById(query.id());
    }

    @Override
    public List<Order> handle (GetAllOrderQuery query){
        return orderRepository.findAll();
    }
}