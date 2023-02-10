package io.github.gxrj.janitory.core.Call;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallService {
    
    @Autowired
    private CallRepository repository;

    public void createOrUpdate( Call entity ) {
        repository.save( entity );
    }

    public void delete( Call entity ) {
        repository.delete( entity );
    }

    public Call findByProtocol( String protocol ) {
        return repository.findByProtocol( protocol ).orElse( null );
    }

    public List<Call> listByStatus( Status status ) {
        return repository.findByStatus( status );
    }

    public List<Call> listByAuthor( String email ) {
        return repository.findByAuthor_Email( email );
    }

    public List<Call> listByDuty( String dutyName ) {
        return repository.findByDuty_Name( dutyName );
    }

    public List<Call> listByDeptName( String deptName ) {
        return repository.findByDestination_Name( deptName );
    }

    public List<Call> listByZipCode( String zipCode ) {
        return repository.findByAddress_ZipCode( zipCode );
    }

    public List<Call> listByDistrict( String districtName ) {
        return repository.findByAddress_District_Name( districtName );
    }

    public List<Call> listByPublicPlace( String pubPlace ) {
        return repository.findByAddress_PubPlace( pubPlace );
    }
}
