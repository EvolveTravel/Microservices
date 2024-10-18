package com.backendtravelbox.cart.domain.model.commands;

public record UpdateCartCommand (Long id,
                                 String product,
                                 String productQuantity,
                                 String cartTotal){
}