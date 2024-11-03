package com.travelbox.api_gateway.fallback.handler;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;


@CircuitBreaker(name = "exterior", fallbackMethod = "fallback")
@Component
public class LoadBalancerHandler implements FallbackHandler {
    public final LoadBalancerClient loadBalancerClient;

    public LoadBalancerHandler(LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
    }

    public ServerResponse handler(String routeId, String url, ServerRequest request) {
        try {
            String instanceUrl = loadBalancerClient.choose(routeId).getUri().toString();
            return HandlerFunctions.http(instanceUrl).handle(request);
        } catch(Exception e) {
            return fallback(request, url, e);
        }
    }

    @Override
    public ServerResponse fallback(ServerRequest request, String url, Exception e) {
        return defaultFallback(request, url, e);
    }
}
