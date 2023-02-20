package io.github.gxrj.janitory.core.Call;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CallService {
    
    @Autowired
    private CallRepository repository;

    private String sortingProperty = "creationDate";

    public void createOrUpdate( Call entity ) {
        repository.saveAndFlush( entity );
    }

    public void delete( Call entity ) {
        repository.delete( entity );
    }

    public Call findByProtocol( String protocol ) {
        return repository.findByProtocol( protocol ).orElse( null );
    }

    public List<Call> listByStatus( Status status ) {
        return repository.findByStatus( status, Sort.by( sortingProperty ).descending() );
    }

    public List<Call> listByAuthor( String email ) {
        return repository.findByAuthor_Email( email, Sort.by( sortingProperty ).descending() );
    }

    public List<Call> listByDuty( String dutyName ) {
        return repository.findByDuty_Name( dutyName, Sort.by( sortingProperty ).descending() );
    }

    public List<Call> listByZipCode( String zipCode ) {
        return repository.findByAddress_ZipCode( zipCode, Sort.by( sortingProperty ).descending() );
    }

    public List<Call> listByDeptName( String deptName ) {
        return repository.findByDestination_Name( deptName, Sort.by( sortingProperty ).descending() );
    }

    public List<Call> listByPublicPlace( String pubPlace ) {
        return repository.findByAddress_PubPlace( pubPlace, Sort.by( sortingProperty ).descending() );
    }

    public List<Call> listByDistrict( String districtName ) {
        return repository.findByAddress_District_Name( districtName, Sort.by( sortingProperty ).descending() );
    }

    public List<Call> listIntervalByDept( LocalDateTime start, LocalDateTime end, String deptName ) {
        return repository.searchByDestinationBetween( start, end, deptName );
    }
}
