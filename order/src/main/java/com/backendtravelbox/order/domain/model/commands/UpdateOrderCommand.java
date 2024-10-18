package com.backendtravelbox.order.domain.model.commands;

public record UpdateOrderCommand(Long id,
                                 Double orderNumber,
                                 String orderDate,
                                 String waitingTime,
                                 Double totalPrice,
                                 String orderStatus,
                                 String paymentMethod,
                                 Double paymentAmount) {
}