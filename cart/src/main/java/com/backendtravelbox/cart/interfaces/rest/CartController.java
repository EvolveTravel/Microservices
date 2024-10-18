package com.backendtravelbox.cart.interfaces.rest;

import com.backendtravelbox.cart.domain.model.commands.DeleteCartCommand;
import com.backendtravelbox.cart.domain.model.queries.GetAllCartQuery;
import com.backendtravelbox.cart.domain.model.queries.GetCartByIdQuery;
import com.backendtravelbox.cart.domain.service.CartCommandService;
import com.backendtravelbox.cart.domain.service.CartQueryService;
import com.backendtravelbox.cart.interfaces.rest.resource.CartResource;
import com.backendtravelbox.cart.interfaces.rest.resource.CreateCartResource;
import com.backendtravelbox.cart.interfaces.rest.resource.UpdateCartResource;
import com.backendtravelbox.cart.interfaces.rest.transform.CartResourceFromEntityAssembler;
import com.backendtravelbox.cart.interfaces.rest.transform.CreateCartCommandFromResourceAssembler;
import com.backendtravelbox.cart.interfaces.rest.transform.UpdateCartCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/carts", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Cart", description = "Cart Management Endpoints")
public class CartController {

    private final CartCommandService cartCommandService;
    private final CartQueryService cartQueryService;

    public CartController(CartCommandService cartCommandService, CartQueryService cartQueryService) {
        this.cartCommandService = cartCommandService;
        this.cartQueryService = cartQueryService;
    }

    @PostMapping
    public ResponseEntity<CartResource> createCart(@RequestBody CreateCartResource createCartResource) {

        var createCartCommand = CreateCartCommandFromResourceAssembler.toCommandFromResource(createCartResource);
        var id = cartCommandService.handle(createCartCommand);
        if (id == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getCartByIdQuery = new GetCartByIdQuery(id);
        var cart = cartQueryService.handle(getCartByIdQuery);
        if (cart.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var cartResource = CartResourceFromEntityAssembler.toResourceFromEntity(cart.get());
        return new ResponseEntity<>(cartResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CartResource>> getAllCarts() {
        var getAllCartQuery = new GetAllCartQuery();
        var cart = cartQueryService.handle(getAllCartQuery);
        var cartResource = cart.stream().map(CartResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(cartResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResource> getCartById(@PathVariable Long id) {
        var getCartByIdQuery = new GetCartByIdQuery(id);
        var cart = cartQueryService.handle(getCartByIdQuery);
        if (cart.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var cartResource = CartResourceFromEntityAssembler.toResourceFromEntity(cart.get());
        return ResponseEntity.ok(cartResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartResource> updateCart(@PathVariable Long id, @RequestBody UpdateCartResource updateCartResource) {
        var updateCartCommand = UpdateCartCommandFromResourceAssembler.toCommandFromResource(id, updateCartResource);
        var updateCart = cartCommandService.handle(updateCartCommand);
        if (updateCart.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var cartResource = CartResourceFromEntityAssembler.toResourceFromEntity(updateCart.get());
        return ResponseEntity.ok(cartResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable Long id) {
        var deleteCartCommand = new DeleteCartCommand(id);
        cartCommandService.handle(deleteCartCommand);
        return ResponseEntity.ok("Cart with given id successfully deleted");
    }
}