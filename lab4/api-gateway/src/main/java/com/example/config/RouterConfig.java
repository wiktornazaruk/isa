package com.example.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("warehouse-service", r -> r
                        .path("/api/warehouses/**")
                        .uri("http://localhost:8091"))
                .route("product-service", r -> r
                        .path("/api/products/**")
                        .uri("http://localhost:8092"))
                .route("warehouse-events", r -> r
                        .path("/api/warehouse-events/**")
                        .uri("http://localhost:8092"))
                .build();
    }
}