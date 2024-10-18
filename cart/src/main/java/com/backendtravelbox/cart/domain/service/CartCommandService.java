package com.backendtravelbox.cart.domain.service;

import com.backendtravelbox.cart.domain.model.aggregates.Cart;
import com.backendtravelbox.cart.domain.model.commands.CreateCartCommand;
import com.backendtravelbox.cart.domain.model.commands.DeleteCartCommand;
import com.backendtravelbox.cart.domain.model.commands.UpdateCartCommand;

import java.util.Optional;

public interface CartCommandService {
    Long handle (CreateCartCommand command);
    Optional<Cart> handle (UpdateCartCommand command);
    void handle (DeleteCartCommand command);
}