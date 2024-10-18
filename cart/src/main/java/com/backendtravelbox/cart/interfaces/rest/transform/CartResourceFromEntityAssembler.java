package com.backendtravelbox.cart.interfaces.rest.transform;

import com.backendtravelbox.cart.domain.model.aggregates.Cart;
import com.backendtravelbox.cart.interfaces.rest.resource.CartResource;

public class CartResourceFromEntityAssembler {
    public static CartResource toResourceFromEntity(Cart entity) {
        return new CartResource(
                entity.getId(),
                entity.getProduct(),
                entity.getProductQuantity(),
                entity.getCartTotal());
    }
}