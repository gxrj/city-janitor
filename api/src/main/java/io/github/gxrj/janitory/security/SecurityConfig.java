package io.github.gxrj.janitory.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import io.github.gxrj.janitory.core.PubAgent.PubAgent;
import io.github.gxrj.janitory.core.PubAgent.PubAgentService;
import io.github.gxrj.janitory.security.applicationKeys.AppKeysService;

import jakarta.annotation.PostConstruct;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    private JWK jwk;

    @Autowired
    private AppKeysService keysService;

    @Autowired
    private PubAgentService agentService;

    @Bean
    SecurityFilterChain filterChain ( HttpSecurity http ) throws Exception {

        http.authorizeHttpRequests( 
            authorize -> authorize
                .requestMatchers( "/anonymous**", "/token**" ).permitAll()
                .requestMatchers( "/user**" ).hasRole( "CITIZEN" )
                .requestMatchers( "/authenticated**" ).hasAnyRole( "ADMIN", "AGENT", "CITIZEN" )
                .requestMatchers( "/agent**" ).hasAnyRole( "ADMIN", "AGENT" )
                .requestMatchers( "/manager**" ).hasRole( "ADMIN" )
                .anyRequest().authenticated()
        )
        .csrf( csrf -> csrf.ignoringRequestMatchers( "/token" ) )
        .userDetailsService( agentDetailsService() )
        .oauth2ResourceServer( config -> config.jwt() )
        .sessionManagement( session -> session.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        var encoders = new HashMap<String, PasswordEncoder>();

        encoders.put( "bcrypt", new BCryptPasswordEncoder() );
        encoders.put( "scrypt", SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8() );
        encoders.put( "argon2", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8() );

        return new DelegatingPasswordEncoder( "argon2", encoders );
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>( new JWKSet( jwk ) );
        return new NimbusJwtEncoder( jwks );
    }

    @Bean
    JwtDecoder jwtDecoder() throws JOSEException {
        return NimbusJwtDecoder.withPublicKey( jwk.toRSAKey().toRSAPublicKey() ).build();
    }

    @PostConstruct
    void init() throws JOSEException {
        jwk = keysService.fetchKeys();
    }

    private UserDetailsService agentDetailsService() {

        return ( login ) -> { 
            var agent = agentService.findByLogin( login );

            if( agent != null ) 
                return User.builder()
                        .username( agent.getLogin() )
                        .password( agent.getPassword() )
                        .authorities( buildAgentRoles( agent ) )
                        .build();

            else
                return null;
        };
    }

    private String[] buildAgentRoles( PubAgent entity ) {
        return !entity.isAdmin() ? 
            new String[]{ "ROLE_ADMIN" }
            : new String[]{ "ROLE_ADMIN", "ROLE_AGENT" };
    }
}
