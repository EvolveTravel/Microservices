package com.backendtravelbox.order.interfaces.rest.resource;

public record OrderResource(Long id,
                            Double orderNumber,
                            String orderDate,
                            String waitingTime,
                            Double totalPrice,
                            String orderStatus,
                            String paymentMethod,
                            Double paymentAmount) {
}