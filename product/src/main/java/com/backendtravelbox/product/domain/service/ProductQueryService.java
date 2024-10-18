package com.backendtravelbox.product.domain.service;

import com.backendtravelbox.product.domain.model.aggregates.Product;
import com.backendtravelbox.product.domain.model.queries.GetAllProductQuery;
import com.backendtravelbox.product.domain.model.queries.GetProductByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {
    List<Product> handle(GetAllProductQuery query);
    Optional<Product> handle(GetProductByIdQuery query);
}