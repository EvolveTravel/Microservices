package com.travelbox.api_gateway.fallback.handler;

import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@FunctionalInterface
public interface FallbackHandler {
    ServerResponse fallback(ServerRequest request, String url, Exception e);

    default ServerResponse defaultFallback(ServerRequest request, String url, Exception e) {
        try {
            return HandlerFunctions.forward(url).handle(request);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
