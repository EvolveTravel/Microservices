package com.travelbox.api_gateway.fallback;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @RequestMapping("/productFallback")
    public ResponseEntity<String> productFallback() {
        return ResponseEntity.status(500).body(
                "Product Service is currently unavailable. Please try again later");
    }

    @RequestMapping("/cartFallback")
    public ResponseEntity<String> cartFallback() {
        return ResponseEntity.status(500).body(
                "Cart Service is currently unavailable. Please try again later");
    }

    @RequestMapping("/userFallback")
    public ResponseEntity<String> userFallback() {
        return ResponseEntity.status(500).body(
                "User Service is currently unavailable. Please try again later");
    }

    @RequestMapping("/orderFallback")
    public ResponseEntity<String> orderFallback() {
        return ResponseEntity.status(500).body(
                "Order Service is currently unavailable. Please try again later");
    }

    @RequestMapping("/tripFallback")
    public ResponseEntity<String> tripFallback() {
        return ResponseEntity.status(500).body(
                "Trip Service is currently unavailable. Please try again later");
    }
}








