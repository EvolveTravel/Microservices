package com.backendtravelbox.order.interfaces.rest.transform;

import com.backendtravelbox.order.domain.model.commands.UpdateOrderCommand;
import com.backendtravelbox.order.interfaces.rest.resource.UpdateOrderResource;

public class UpdateOrderCommandFromResourceAssembler {
    public static UpdateOrderCommand toCommandFromResource(Long id, UpdateOrderResource resource){
        return new UpdateOrderCommand(
                id,
                resource.orderNumber(),
                resource.orderDate(),
                resource.waitingTime(),
                resource.totalPrice(),
                resource.orderStatus(),
                resource.paymentMethod(),
                resource.paymentAmount());
    }
}