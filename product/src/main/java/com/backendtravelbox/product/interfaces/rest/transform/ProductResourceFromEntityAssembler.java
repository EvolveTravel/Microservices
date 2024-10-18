package com.backendtravelbox.product.interfaces.rest.transform;

import com.backendtravelbox.product.domain.model.aggregates.Product;
import com.backendtravelbox.product.interfaces.rest.resource.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product entity ) {
        return new ProductResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getImageUrl(),
                entity.getRating(),
                entity.getCategory());
    }
}