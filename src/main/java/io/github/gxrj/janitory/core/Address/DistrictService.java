package io.github.gxrj.janitory.core.Address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictService {
    
    @Autowired
    private DistrictRepository repository;

    public List<District> findAll() {
        return repository.findAll();
    }

    public void createOrUpdate( District entity ) {
        repository.save( entity );
    }

    public void delete( District entity ) {
        repository.delete( entity );
    }
}
