package io.github.gxrj.janitory.security.applicationKeys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWK;

import io.github.gxrj.janitory.utils.KeyGenerator;

@Service
public class AppKeysService {
    
    @Autowired
    private AppKeysRepository keysRepository;

    public JWK fetchKeys() throws JOSEException {
        return keysRepository.count() > 0 ? 
            keysRepository.findAll().get( 0 ).getKeyPair() :
            generate().getKeyPair();
    }

    private AppKeys generate() throws JOSEException {
        var appKeys = AppKeys.builder()
                            .keyPair( KeyGenerator.getRsaKeys() )
                            .build();

        return keysRepository.save( appKeys );
    }
}
