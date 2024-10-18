package com.backendtravelbox.order.domain.model.commands;

public record CreateOrderCommand(Double orderNumber,
                                 String orderDate,
                                 String waitingTime,
                                 Double totalPrice,
                                 String orderStatus,
                                 String paymentMethod,
                                 Double paymentAmount) {
}