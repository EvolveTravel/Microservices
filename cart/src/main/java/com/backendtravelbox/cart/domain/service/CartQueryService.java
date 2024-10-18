package com.backendtravelbox.cart.domain.service;

import com.backendtravelbox.cart.domain.model.aggregates.Cart;
import com.backendtravelbox.cart.domain.model.queries.GetAllCartQuery;
import com.backendtravelbox.cart.domain.model.queries.GetCartByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CartQueryService {
    List<Cart> handle (GetAllCartQuery query);
    Optional<Cart> handle (GetCartByIdQuery query);
}