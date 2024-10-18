package com.backendtravelbox.order.domain.model.aggregates;

import com.backendtravelbox.order.domain.model.commands.CreateOrderCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
@Table(name = "Orders")
public class Order extends AbstractAggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private Double orderNumber;

    @Getter
    private String orderDate;

    @Getter
    private String waitingTime;

    @Getter
    private Double totalPrice;

    @Getter
    private String orderStatus;

    @Getter
    private String paymentMethod;

    @Getter
    private Double paymentAmount;

    public Order(){
        this.orderDate = Strings.EMPTY;
        this.waitingTime = Strings.EMPTY;
        this.orderStatus= Strings.EMPTY;
        this.paymentMethod= Strings.EMPTY;
    }

    public Order (Double orderNumber, String orderDate, String waitingTime, Double totalPrice, String orderStatus, String paymentMethod, Double paymentAmount) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.waitingTime = waitingTime;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
    }

    public Order (CreateOrderCommand command){
        this();
        this.orderNumber = command.orderNumber();
        this.orderDate = command.orderDate();
        this.waitingTime = command.waitingTime();
        this.totalPrice = command.totalPrice();
        this.orderStatus = command.orderStatus();
        this.paymentMethod = command.paymentMethod();
        this.paymentAmount = command.paymentAmount();
    }

    public Order updateOrder(Double orderNumber, String orderDate, String waitingTime, Double totalPrice, String orderStatus, String paymentMethod, Double paymentAmount){
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.waitingTime = waitingTime;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
        return this;
    }
}