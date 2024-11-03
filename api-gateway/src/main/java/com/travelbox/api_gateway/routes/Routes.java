package com.travelbox.api_gateway.routes;

import com.travelbox.api_gateway.fallback.handler.LoadBalancerHandler;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;


@Configuration
public class Routes {

    private final LoadBalancerHandler loadBalancerHandler;
    public Routes(LoadBalancerHandler loadBalancerHandler) {
        this.loadBalancerHandler = loadBalancerHandler;
    }

    private RouterFunction<ServerResponse> loadBalancedTravelBoxRoute(
            String routeId,
            String path,
            String fallbackUrl
    ) {
        String basePath = "/api/v1/";
        return GatewayRouterFunctions.route(routeId)
                .route(RequestPredicates.path(basePath + path),
                        request -> loadBalancerHandler.handler( routeId, fallbackUrl, request))
                .build();
    }

    private RouterFunction<ServerResponse> generalTravelBoxRoute(
            String routeId, String path, String url
    ) {
        String basePath = "/api/v1/";
        return GatewayRouterFunctions.route(routeId)
                .route(RequestPredicates.path(basePath + path)
                        , HandlerFunctions.http(url))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return loadBalancedTravelBoxRoute(
                "product",
                "products",
                "/productFallback"
        );
    }

    @Bean
    public RouterFunction<ServerResponse> cartServiceRoute() {
        return loadBalancedTravelBoxRoute(
                "cart",
                "carts",
                "/cartFallback"
        );
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {
        return loadBalancedTravelBoxRoute(
                "user",
                "users",
                "/userFallback"
        );
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return loadBalancedTravelBoxRoute(
                "order",
                "orders",
                "/orderFallback"
        );
    }

    @Bean
    public RouterFunction<ServerResponse> tripServiceRoute() {
        return loadBalancedTravelBoxRoute(
                "trip",
                "trips",
                "/tripFallback"
        );
    }

    /*
    static private RouterFunction<ServerResponse> generalTravelBoxRoute(String routeId, String path, String url){
        String basePath = "/api/v1/";
        return GatewayRouterFunctions.route(routeId).
                route(RequestPredicates.path(basePath + path), HandlerFunctions.http(url)).build();
    }


    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {

        return GatewayRouterFunctions.route("product")
                .route(RequestPredicates.path("/api/v1/products"),
                        HandlerFunctions.http("http://localhost:8091")).build();
    }

    @Bean
    public RouterFunction<ServerResponse> cartServiceRoute() {
        return GatewayRouterFunctions.route("cart")
                .route(RequestPredicates.path("/api/v1/carts"),
                        HandlerFunctions.http("http://localhost:8092")).build();
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {
        return GatewayRouterFunctions.route("user")
                .route(RequestPredicates.path("/api/v1/users"),
                        HandlerFunctions.http("http://localhost:8093")).build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return GatewayRouterFunctions.route("orders")
                .route(RequestPredicates.path("/api/v1/orders"),
                        HandlerFunctions.http("http://localhost:8094")).build();
    }

    @Bean
    public RouterFunction<ServerResponse> tripServiceRoute() {
        return GatewayRouterFunctions.route("trip")
                .route(RequestPredicates.path("/api/v1/trips"),
                        HandlerFunctions.http("http://localhost:8095")).build();
    }


    */
}
