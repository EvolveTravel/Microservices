package com.backendtravelbox.product.domain.service;

import com.backendtravelbox.product.domain.model.aggregates.Product;
import com.backendtravelbox.product.domain.model.commands.CreateProductCommand;
import com.backendtravelbox.product.domain.model.commands.DeleteProductCommand;
import com.backendtravelbox.product.domain.model.commands.UpdateProductCommand;

import java.util.Optional;

public interface ProductCommandService {
    Long handle (CreateProductCommand command);
    Optional<Product> handle (UpdateProductCommand command);
    void handle (DeleteProductCommand command);
}