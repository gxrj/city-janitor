package io.github.gxrj.janitory.core.Duty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DutyService {
 
    @Autowired
    private DutyRepository repository;

    public void createOrUpdate( Duty entity ) {
        repository.save( entity );
    }

    public Duty findByName( String name ) {
        return repository.findByName( name ).orElse( null );
    }

    public List<Duty> listAll() {
        return repository.findAll();
    }

    public List<Duty> listByDeptName( String deptName ) {
        return repository.findByDept_Name( deptName );
    }

    public List<Duty> listByGroupName( String groupName ) {
        return repository.findByGroup_Name( groupName );
    }
}
