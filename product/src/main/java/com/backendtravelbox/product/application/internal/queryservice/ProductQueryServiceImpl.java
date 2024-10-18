package com.backendtravelbox.product.application.internal.queryservice;

import com.backendtravelbox.product.domain.model.aggregates.Product;
import com.backendtravelbox.product.domain.model.queries.GetAllProductQuery;
import com.backendtravelbox.product.domain.model.queries.GetProductByIdQuery;
import com.backendtravelbox.product.domain.service.ProductQueryService;
import com.backendtravelbox.product.infraestructure.persistance.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;

    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> handle(GetProductByIdQuery query) {
        return productRepository.findById(query.id());
    }

    @Override
    public List<Product> handle(GetAllProductQuery query) {
        return productRepository.findAll();
    }
}