package com.backendtravelbox.product.application.internal.commandservice;

import com.backendtravelbox.product.domain.model.aggregates.Product;
import com.backendtravelbox.product.domain.model.commands.CreateProductCommand;
import com.backendtravelbox.product.domain.model.commands.DeleteProductCommand;
import com.backendtravelbox.product.domain.model.commands.UpdateProductCommand;
import com.backendtravelbox.product.domain.service.ProductCommandService;
import com.backendtravelbox.product.infraestructure.persistance.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;
    public ProductCommandServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Long handle(CreateProductCommand command) {
        if (productRepository.existsByName(command.name())){
            throw new IllegalArgumentException("Product Already Exists");
        }
        Product product = new Product(command);
        try {
            productRepository.save(product);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving user" + e.getMessage());
        }
        return product.getId();
    }

    @Override
    public Optional<Product> handle (UpdateProductCommand command) {

        if (productRepository.existsByNameAndIdIsNot(command.name(), command.id())){
            throw new IllegalArgumentException("Product with same name already exists");
        }

        var result = productRepository.findById(command.id());
        if (result.isEmpty()){
            throw new IllegalArgumentException("Product does not exist");
        }

        var productToUpdate = result.get();
        try {
            var updatedProduct = productRepository.save(productToUpdate.updateProduct(
                    command.name(),
                    command.description(),
                    command.price(),
                    command.imageUrl(),
                    command.rating(),
                    command.category()));
            return Optional.of(updatedProduct);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving user" + e.getMessage());
        }
    }

    @Override
    public void handle (DeleteProductCommand command) {

        if (!productRepository.existsById(command.id())){
            throw new IllegalArgumentException("Product does not exist");
        }
        try {
            productRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting product" + e.getMessage());
        }
    }
}