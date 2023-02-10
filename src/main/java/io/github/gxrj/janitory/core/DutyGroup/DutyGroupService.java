package io.github.gxrj.janitory.core.DutyGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DutyGroupService {
 
    @Autowired
    private DutyGroupRepository repository;

    public List<DutyGroup> listAll() {
        return repository.findAll();
    }

    public DutyGroup findByName( String name ) {
        return repository.findByName( name ).orElse( null );
    }

    public void createOrUpdate( DutyGroup entity ) {
        repository.save( entity );
    }

    public void delete( DutyGroup entity ) {
        repository.delete( entity );
    }
}
