package com.backendtravelbox.order.application.internal.commandservice;

import com.backendtravelbox.order.domain.model.aggregates.Order;
import com.backendtravelbox.order.domain.model.commands.CreateOrderCommand;
import com.backendtravelbox.order.domain.model.commands.DeleteOrderCommand;
import com.backendtravelbox.order.domain.model.commands.UpdateOrderCommand;
import com.backendtravelbox.order.domain.service.OrderCommandService;
import com.backendtravelbox.order.infraestructure.persistance.jpa.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {

    private final OrderRepository orderRepository;
    public OrderCommandServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Long handle(CreateOrderCommand command) {
        if (orderRepository.existsByOrderNumber(command.orderNumber())){
            throw new IllegalArgumentException("Order Already Exists");
        }
        Order order = new Order();
        try {
            orderRepository.save(order);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving order" + e.getMessage());
        }
        return order.getId();
    }

    @Override
    public Optional<Order> handle (UpdateOrderCommand command) {

        if (orderRepository.existsByOrderNumberAndIdIsNot(command.orderNumber(), command.id())){
            throw new IllegalArgumentException("Order with same order number already exist");
        }

        var result = orderRepository.findById(command.id());
        if (result.isEmpty()){
            throw new IllegalArgumentException("Order does not exist");
        }

        var orderToUpdate = result.get();
        try {
            var updatedOrder = orderRepository.save(orderToUpdate.updateOrder(
                    command.orderNumber(),
                    command.orderDate(),
                    command.waitingTime(),
                    command.totalPrice(),
                    command.orderStatus(),
                    command.paymentMethod(),
                    command.paymentAmount()));
            return Optional.of(updatedOrder);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving order" + e.getMessage());
        }
    }

    @Override
    public void handle (DeleteOrderCommand command) {

        if (!orderRepository.existsById(command.id())){
            throw new IllegalArgumentException("Order does not exist");
        }
        try {
            orderRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting order" + e.getMessage());
        }
    }
}