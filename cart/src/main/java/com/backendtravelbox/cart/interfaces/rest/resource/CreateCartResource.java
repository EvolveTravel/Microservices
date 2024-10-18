package com.backendtravelbox.cart.interfaces.rest.resource;

public record CreateCartResource(String product,
                                 String productQuantity,
                                 String cartTotal) {
}
