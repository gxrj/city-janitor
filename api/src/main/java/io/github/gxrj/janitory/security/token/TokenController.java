package io.github.gxrj.janitory.security.token;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public String login( @RequestBody PubAgentDto credentials ) {
        var account = agentService.findByLogin( credentials.getLogin() );

        if( isInvalidLogin( account, credentials) ) 
            return PlainJson.builder()
                            .append( "error", "Login ou senha inválidos!" )
                            .build();

        var tokenLifeSpan = 28800L; // 480 minutes = 8 hours

        return PlainJson.builder()
                        .append( "access_token", buildTokenFromEntity( account, tokenLifeSpan ) )
                        .build();
    }

    private boolean isInvalidLogin( PubAgent account, PubAgentDto credentials ) {
        if( account == null ) return true;
        if( !passwordEncoder.matches( credentials.getPassword(), account.getPassword() ) ) return true;
        else return false;
    }

    private String buildTokenFromEntity( PubAgent account, long secondsToLive ) {
        var username = account.getLogin();
        var claims = new HashMap<String, Object>();
        claims.put( "name", account.getName() );
        claims.put( "dept", account.getDept() );
        claims.put( "roles", buildRolesByAccount( account ) );
        return generateToken( username, claims, secondsToLive );
    }

    private String generateToken( String username, Map<String,Object> attributes, long secondsToLive ) {

        var now = Instant.now();
        var expirationTime = now.plusSeconds( secondsToLive );

        var claims = JwtClaimsSet.builder()
                                .issuedAt( now )
                                .issuer( "self" )
                                .subject( username )
                                .expiresAt( expirationTime );

        attributes.forEach( (key, value) -> claims.claim( key, value ) );

        return jwtEncoder.encode( JwtEncoderParameters.from( claims.build() ) ).getTokenValue();
    }

    private String buildRolesByAccount( PubAgent account) {
        return account.isAdmin() ? "ROLE_ADMIN ROLE_AGENT" : "ROLE_AGENT";
    }
}
