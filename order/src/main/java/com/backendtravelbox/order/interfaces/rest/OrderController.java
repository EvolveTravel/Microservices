package com.backendtravelbox.order.interfaces.rest;

import com.backendtravelbox.order.domain.model.commands.DeleteOrderCommand;
import com.backendtravelbox.order.domain.model.queries.GetAllOrderQuery;
import com.backendtravelbox.order.domain.model.queries.GetOrderByIdQuery;
import com.backendtravelbox.order.domain.service.OrderCommandService;
import com.backendtravelbox.order.domain.service.OrderQueryService;
import com.backendtravelbox.order.interfaces.rest.resource.CreateOrderResource;
import com.backendtravelbox.order.interfaces.rest.resource.OrderResource;
import com.backendtravelbox.order.interfaces.rest.resource.UpdateOrderResource;
import com.backendtravelbox.order.interfaces.rest.transform.CreateOrderCommandFromResourceAssembler;
import com.backendtravelbox.order.interfaces.rest.transform.OrderResourceFromEntityAssembler;
import com.backendtravelbox.order.interfaces.rest.transform.UpdateOrderCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/orders", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Order", description = "Order Management Endpoints")
public class OrderController {

    private final OrderCommandService orderCommandService;
    private final OrderQueryService orderQueryService;

    public OrderController(OrderCommandService orderCommandService, OrderQueryService orderQueryService){
        this.orderCommandService = orderCommandService;
        this.orderQueryService = orderQueryService;
    }

    @PostMapping
    public ResponseEntity<OrderResource> createOrder(@RequestBody CreateOrderResource createOrderResource){

        var createOrderCommand = CreateOrderCommandFromResourceAssembler.toCommandFromResource(createOrderResource);
        var id = orderCommandService.handle(createOrderCommand);
        if (id == 0L){
            return ResponseEntity.badRequest().build();
        }

        var getOrdersByIdQuery = new GetOrderByIdQuery(id);
        var order = orderQueryService.handle(getOrdersByIdQuery);
        if (order.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var orderResource = OrderResourceFromEntityAssembler.toResourceFromEntity(order.get());
        return new ResponseEntity<>(orderResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderResource>> getAllOrders(){
        var getAllOrderQuery = new GetAllOrderQuery();
        var order = orderQueryService.handle(getAllOrderQuery);
        var orderResource = order.stream().map(OrderResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(orderResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResource> getOrderById(@PathVariable Long id){
        var getOrderByIdQuery = new GetOrderByIdQuery(id);
        var order = orderQueryService.handle(getOrderByIdQuery);
        if (order.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var orderResource = OrderResourceFromEntityAssembler.toResourceFromEntity(order.get());
        return ResponseEntity.ok(orderResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResource> updateOrder (@PathVariable Long id, @RequestBody UpdateOrderResource updateOrderResource){
        var updateOrderCommand = UpdateOrderCommandFromResourceAssembler.toCommandFromResource(id, updateOrderResource);
        var updateOrder = orderCommandService.handle(updateOrderCommand);
        if (updateOrder.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var orderResource = OrderResourceFromEntityAssembler.toResourceFromEntity(updateOrder.get());
        return ResponseEntity.ok(orderResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        var deleteOrderCommand = new DeleteOrderCommand(id);
        orderCommandService.handle(deleteOrderCommand);
        return ResponseEntity.ok("Order with given id successfully deleted");
    }
}