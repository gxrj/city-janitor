package io.github.gxrj.janitory.security.token;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.gxrj.janitory.core.PubAgent.PubAgent;
import io.github.gxrj.janitory.core.PubAgent.PubAgentDto;
import io.github.gxrj.janitory.core.PubAgent.PubAgentService;
import io.github.gxrj.janitory.utils.PlainJson;

@RestController
public class TokenController {

    @Autowired
    private PubAgentService agentService;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping( "/token" )
    public String login( PubAgentDto credentials ) {
        var account = agentService.findByLogin( credentials.getLogin() );

        if( isInvalidLogin( account, credentials) ) 
            return PlainJson.builder()
                            .append( "error", "Login ou senha inválidos!" )
                            .build();

        var durationInSeconds = 300L; // 5 minutes

        return PlainJson.builder()
                        .append( "access_token", buildTokenFromEntity( account, durationInSeconds ) )
                        .build();
    }

    @GetMapping( "/agent/refresh" )
    public String refresh( Authentication auth ) {
        var durationInSeconds = 900L; // 15 minutes

        return PlainJson.builder()
                        .append( "token", buildTokenFromAuthentication( auth, durationInSeconds ) )
                        .build();
    }

    private boolean isInvalidLogin( PubAgent account, PubAgentDto credentials ) {
        if( account == null ) return true;
        if( !passwordEncoder.matches( credentials.getPassword(), account.getPassword() ) ) return true;
        else return false;
    }

    private String buildTokenFromAuthentication( Authentication account, long secondsToLive ) {
        var username = account.getPrincipal().toString();
        var roles = account.getAuthorities().parallelStream()
                            .map( GrantedAuthority::getAuthority )
                            .collect( Collectors.joining( " " ) );

        return generateToken( username, roles, secondsToLive );
    }

    private String buildTokenFromEntity( PubAgent account, long secondsToLive ) {
        var username = account.getLogin();
        var roles = account.isAdmin() ? "ROLE_ADMIN ROLE_AGENT" : "ROLE_AGENT";
        return generateToken( username, roles, secondsToLive );
    }

    private String generateToken( String username, String roles, long secondsToLive ) {

        var now = Instant.now();
        var expirationTime = now.plusSeconds( secondsToLive );

        var claims = JwtClaimsSet.builder()
                                .issuedAt( now )
                                .issuer( "self" )
                                .subject( username )
                                .claim( "roles", roles )
                                .expiresAt( expirationTime )
                                .build();

        return jwtEncoder.encode( JwtEncoderParameters.from( claims ) ).getTokenValue();
    }
}
