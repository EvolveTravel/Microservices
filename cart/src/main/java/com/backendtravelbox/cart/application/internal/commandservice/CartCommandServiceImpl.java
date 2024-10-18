package com.backendtravelbox.cart.application.internal.commandservice;

import com.backendtravelbox.cart.domain.model.aggregates.Cart;
import com.backendtravelbox.cart.domain.model.commands.CreateCartCommand;
import com.backendtravelbox.cart.domain.model.commands.DeleteCartCommand;
import com.backendtravelbox.cart.domain.model.commands.UpdateCartCommand;
import com.backendtravelbox.cart.domain.service.CartCommandService;
import com.backendtravelbox.cart.infraestructure.persistance.jpa.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartCommandServiceImpl implements CartCommandService {

    public final CartRepository cartRepository;
    public CartCommandServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Long handle (CreateCartCommand command) {
        if (cartRepository.existsByProduct(command.product())){
            throw new IllegalArgumentException("Cart already exists");
        }

        Cart cart = new Cart(command);
        try {
            cartRepository.save(cart);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving trip" + e.getMessage());
        }
        return cart.getId();
    }

    @Override
    public Optional<Cart> handle(UpdateCartCommand command) {
        if (cartRepository.existsByProductAndIdIsNot(command.product(), command.id())){
            throw new IllegalArgumentException("Cart with same product already exists");
        }

        var result = cartRepository.findById(command.id());
        if (result.isEmpty()){
            throw new IllegalArgumentException("Cart does not exist");
        }

        var cartToUpdate = result.get();
        try {
            var updatedCart = cartRepository.save(cartToUpdate.updateCart(
                    command.product(),
                    command.productQuantity(),
                    command.cartTotal()));
            return Optional.of(updatedCart);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving cart" + e.getMessage());
        }
    }

    @Override
    public void handle (DeleteCartCommand command) {
        if(!cartRepository.existsById(command.id())){
            throw new IllegalArgumentException("Cart does not exist");
        }
        try {
            cartRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting cart" + e.getMessage());
        }
    }
}