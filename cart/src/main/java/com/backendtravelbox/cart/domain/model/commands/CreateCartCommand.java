package com.backendtravelbox.cart.domain.model.commands;

public record CreateCartCommand(String product,
                                String productQuantity,
                                String cartTotal) {
}