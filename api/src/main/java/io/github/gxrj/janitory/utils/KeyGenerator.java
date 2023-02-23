package io.github.gxrj.janitory.utils;

import java.util.UUID;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;

public class KeyGenerator {
    public static JWK getRsaKeys() throws JOSEException {
        return new RSAKeyGenerator( 2048 )
                    .keyID( UUID.randomUUID().toString() )
                    .keyUse( KeyUse.SIGNATURE )
                    .algorithm( new Algorithm( "RSA256" ) )
                    .generate();
    }
}
