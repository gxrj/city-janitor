package io.github.gxrj.janitory.core.Citizen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitizenService {

    @Autowired
    private CitizenRepository repository;

    public void createOrUpdate( Citizen entity ) {
        repository.save( entity );
    }

    public void delete( Citizen entity ) {
        repository.delete( entity );
    }

    public Citizen findByEmail( String email ) {
        return repository.findByEmail( email ).orElse( null );
    }
}
