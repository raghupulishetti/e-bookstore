package com.dailycoder.bookstore.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("PRODUCT-SERVICE", r -> r.path("/productservice/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://PRODUCT-SERVICE"))


                .route("USER-SERVICE", r -> r.path("/userservice/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://USER-SERVICE"))

               /* .route("AUTH-SERVICE", r -> r.path("/authservice/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://AUTH-SERVICE"))*/
                .build();
    }

}
