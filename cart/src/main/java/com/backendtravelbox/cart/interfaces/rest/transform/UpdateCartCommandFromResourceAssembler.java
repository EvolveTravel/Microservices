package com.backendtravelbox.cart.interfaces.rest.transform;

import com.backendtravelbox.cart.domain.model.commands.UpdateCartCommand;
import com.backendtravelbox.cart.interfaces.rest.resource.UpdateCartResource;

public class UpdateCartCommandFromResourceAssembler {
    public static UpdateCartCommand toCommandFromResource(Long id, UpdateCartResource resource) {
        return new UpdateCartCommand(
                id,
                resource.product(),
                resource.productQuantity(),
                resource.cartTotal());
    }
}