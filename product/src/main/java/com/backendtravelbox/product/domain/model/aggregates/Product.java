package com.backendtravelbox.product.domain.model.aggregates;

import com.backendtravelbox.product.domain.model.commands.CreateProductCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
@Table(name = "Product")
public class Product extends AbstractAggregateRoot<Product> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private String description;

    @Setter
    @Getter
    private Double price;

    @Getter
    private String imageUrl;

    @Getter
    private Double rating;

    @Getter
    private String category;

    public Product() {
        this.name = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.imageUrl = Strings.EMPTY;
        this.category = Strings.EMPTY;
        this.price = 0.0;
    }

    public Product(String name, String description, Double price, String imageUrl, Double rating, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.category = category;
    }

    public Product(CreateProductCommand command){
        this();
        this.name = command.name();
        this.description = command.description();
        this.price = command.price();
        this.imageUrl = command.imageUrl();
        this.rating = command.rating();
        this.category = command.category();
    }

    public Product updateProduct(String name, String description, Double price, String imageUrl, Double rating, String category){
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.category = category;
        return this;
    }
}