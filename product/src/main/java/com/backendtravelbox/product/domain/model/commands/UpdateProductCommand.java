package com.backendtravelbox.product.domain.model.commands;

public record UpdateProductCommand(Long id,
                                   String name,
                                   String description,
                                   Double price,
                                   String imageUrl,
                                   Double rating,
                                   String category) {
}