package com.backendtravelbox.product.interfaces.rest.resource;

public record CreateProductResource(String name,
                                    String description,
                                    Double price,
                                    String imageUrl,
                                    Double rating,
                                    String category) {
}