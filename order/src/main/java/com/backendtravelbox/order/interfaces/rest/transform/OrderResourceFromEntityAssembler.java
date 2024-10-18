package com.backendtravelbox.order.interfaces.rest.transform;

import com.backendtravelbox.order.domain.model.aggregates.Order;
import com.backendtravelbox.order.interfaces.rest.resource.OrderResource;

public class OrderResourceFromEntityAssembler {
    public static OrderResource toResourceFromEntity(Order entity) {
        return new OrderResource(
                entity.getId(),
                entity.getOrderNumber(),
                entity.getOrderDate(),
                entity.getWaitingTime(),
                entity.getTotalPrice(),
                entity.getOrderStatus(),
                entity.getPaymentMethod(),
                entity.getPaymentAmount());
    }
}