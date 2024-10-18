package com.backendtravelbox.product.interfaces.rest.transform;

import com.backendtravelbox.product.domain.model.commands.UpdateProductCommand;
import com.backendtravelbox.product.interfaces.rest.resource.UpdateProductResource;

public class UpdateProductCommandFromResourceAssembler {
    public static UpdateProductCommand toCommandFromResource(Long id, UpdateProductResource resource) {
        return new UpdateProductCommand(
                id,
                resource.name(),
                resource.description(),
                resource.price(),
                resource.imageUrl(),
                resource.rating(),
                resource.category());
    }
}