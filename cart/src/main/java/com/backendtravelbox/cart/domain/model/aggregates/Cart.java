package com.backendtravelbox.cart.domain.model.aggregates;

import com.backendtravelbox.cart.domain.model.commands.CreateCartCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
@Table(name = "Cart")
public class Cart extends AbstractAggregateRoot<Cart> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String product;

    @Getter
    private String productQuantity;

    @Getter
    private String cartTotal;

    public Cart() {
        this.product = Strings.EMPTY;
        this.productQuantity = Strings.EMPTY;
        this.cartTotal = Strings.EMPTY;
    }

    public Cart(String product, String productQuantity, String cartTotal) {
        this.product = product;
        this.productQuantity = productQuantity;
        this.cartTotal = cartTotal;
    }

    public Cart(CreateCartCommand command){
        this();
        this.product = command.product();
        this.productQuantity = command.productQuantity();
        this.cartTotal = command.cartTotal();
    }

    public Cart updateCart(String product, String productQuantity, String cartTotal) {
        this.product = product;
        this.productQuantity = productQuantity;
        this.cartTotal = cartTotal;
        return this;
    }
}