package com.backendtravelbox.cart.interfaces.rest.resource;

public record CartResource(Long id,
                           String product,
                           String productQuantity,
                           String cartTotal) {
}
