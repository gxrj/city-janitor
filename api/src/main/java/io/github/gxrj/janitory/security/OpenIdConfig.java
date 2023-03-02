package io.github.gxrj.janitory.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Order(1)
@Configuration
public class OpenIdConfig {

    @Bean
    SecurityFilterChain oidcFilterChain( HttpSecurity http ) throws Exception {
        http.securityMatcher( "/user**" );
        return http.build();
    }
}