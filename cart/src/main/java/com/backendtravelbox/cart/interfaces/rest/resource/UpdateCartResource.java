package com.backendtravelbox.cart.interfaces.rest.resource;

public record UpdateCartResource(String product,
                                  String productQuantity,
                                  String cartTotal) {
}