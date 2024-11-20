package com.example.api_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("StockService", r -> r
                        .path("/api/addItems/**")
                        .uri("http://localhost:8080/"))
                .route("OrderService", r -> r
                        .path("/api/orders/**")
                        .filters(f -> f.addRequestHeader("X-Order-Service", "OrderHeader"))
                        .uri("http://localhost:8082/"))
                .route("AccountService", r -> r
                        .path("/api/account/**")
                        .uri("http://localhost:8084/"))
                .build();
    }
}