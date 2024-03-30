package com.devstack.lms.gatewayserviceapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity){
        serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
               /* .cors(httpSecurityCorsConfigurer ->
                        httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()))*/
                .authorizeExchange(authorize ->
                        authorize
                                .pathMatchers("/api/v1/users/**").permitAll()
                                .pathMatchers("/eureka/**")
                        .permitAll()
                        .anyExchange()
                        .authenticated())
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);

        return  serverHttpSecurity.build();
    }

  /*  @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // Set allowed headers
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        // Set allowed origins
        corsConfiguration.setAllowedOrigins(List.of("*"));
        // Set allowed methods
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT", "OPTIONS", "PATCH", "DELETE"));
        // Set whether credentials are allowed
        corsConfiguration.setAllowCredentials(false);
        // Set exposed headers
        corsConfiguration.setExposedHeaders(List.of("Authorization"));
        // Set max age
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }*/

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(List.of("http://localhost:4200")); // Specify your allowed origin
        corsConfig.setAllowedMethods(List.of("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.addAllowedHeader("*");

        org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource source =
                new org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

}
