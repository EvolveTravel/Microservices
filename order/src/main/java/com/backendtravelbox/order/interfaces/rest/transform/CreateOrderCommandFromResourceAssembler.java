package com.backendtravelbox.order.interfaces.rest.transform;

import com.backendtravelbox.order.domain.model.commands.CreateOrderCommand;
import com.backendtravelbox.order.interfaces.rest.resource.CreateOrderResource;

public class CreateOrderCommandFromResourceAssembler {
    public static CreateOrderCommand toCommandFromResource(CreateOrderResource resource) {
        return new CreateOrderCommand(
                resource.orderNumber(),
                resource.orderDate(),
                resource.waitingTime(),
                resource.totalPrice(),
                resource.orderStatus(),
                resource.paymentMethod(),
                resource.paymentAmount());
    }
}