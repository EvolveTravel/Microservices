package com.backendtravelbox.cart.interfaces.rest.transform;

import com.backendtravelbox.cart.domain.model.commands.CreateCartCommand;
import com.backendtravelbox.cart.interfaces.rest.resource.CreateCartResource;

public class CreateCartCommandFromResourceAssembler {
    public static CreateCartCommand toCommandFromResource (CreateCartResource resource) {
        return new CreateCartCommand(
                resource.product(),
                resource.productQuantity(),
                resource.cartTotal());
    }
}