package com.backendtravelbox.cart.application.internal.queryservice;

import com.backendtravelbox.cart.domain.model.aggregates.Cart;
import com.backendtravelbox.cart.domain.model.queries.GetAllCartQuery;
import com.backendtravelbox.cart.domain.model.queries.GetCartByIdQuery;
import com.backendtravelbox.cart.domain.service.CartQueryService;
import com.backendtravelbox.cart.infraestructure.persistance.jpa.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartQueryServiceImpl implements CartQueryService {

    private final CartRepository cartRepository;

    public CartQueryServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<Cart> handle(GetCartByIdQuery query) {
        return cartRepository.findById(query.id());
    }

    @Override
    public List<Cart> handle(GetAllCartQuery query) {
        return cartRepository.findAll();
    }
}