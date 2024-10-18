package com.backendtravelbox.product.interfaces.rest.transform;

import com.backendtravelbox.product.domain.model.commands.CreateProductCommand;
import com.backendtravelbox.product.interfaces.rest.resource.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand toCommandFromResource(CreateProductResource resource) {
        return new CreateProductCommand(
                resource.name(),
                resource.description(),
                resource.price(),
                resource.imageUrl(),
                resource.rating(),
                resource.category());
    }
}