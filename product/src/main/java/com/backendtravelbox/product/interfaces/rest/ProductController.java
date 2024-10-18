package com.backendtravelbox.product.interfaces.rest;

import com.backendtravelbox.product.domain.model.commands.DeleteProductCommand;
import com.backendtravelbox.product.domain.model.queries.GetAllProductQuery;
import com.backendtravelbox.product.domain.model.queries.GetProductByIdQuery;
import com.backendtravelbox.product.domain.service.ProductCommandService;
import com.backendtravelbox.product.domain.service.ProductQueryService;
import com.backendtravelbox.product.interfaces.rest.resource.CreateProductResource;
import com.backendtravelbox.product.interfaces.rest.resource.ProductResource;
import com.backendtravelbox.product.interfaces.rest.resource.UpdateProductResource;
import com.backendtravelbox.product.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import com.backendtravelbox.product.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import com.backendtravelbox.product.interfaces.rest.transform.UpdateProductCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/products", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Product", description = "Product Management Endpoints")
public class ProductController {

    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    public ProductController(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    @PostMapping
    public ResponseEntity<ProductResource> createProduct(@RequestBody CreateProductResource createProductResource){

        var createProductCommand = CreateProductCommandFromResourceAssembler.toCommandFromResource(createProductResource);
        var id = productCommandService.handle(createProductCommand);
        if (id == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getProductByIdQuery = new GetProductByIdQuery(id);
        var product = productQueryService.handle(getProductByIdQuery);
        if (product.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return new ResponseEntity<>(productResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResource>> getAllProducts() {
        var getAllProductQuery = new GetAllProductQuery();
        var product = productQueryService.handle(getAllProductQuery);
        var productResource = product.stream().map(ProductResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(productResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResource> getProductById(@PathVariable Long id) {
        var getProductByIdQuery = new GetProductByIdQuery(id);
        var product = productQueryService.handle(getProductByIdQuery);
        if (product.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());
        return ResponseEntity.ok(productResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResource> updateProduct(@PathVariable Long id, @RequestBody UpdateProductResource updateProductResource) {
        var updateProductCommand = UpdateProductCommandFromResourceAssembler.toCommandFromResource(id, updateProductResource);
        var updateProduct = productCommandService.handle(updateProductCommand);
        if (updateProduct.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(updateProduct.get());
        return ResponseEntity.ok(productResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        var deleteProductCommand = new DeleteProductCommand(id);
        productCommandService.handle(deleteProductCommand);
        return ResponseEntity.ok("Product with given id successfully deleted");
    }
}