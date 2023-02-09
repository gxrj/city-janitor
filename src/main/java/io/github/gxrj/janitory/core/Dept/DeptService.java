package io.github.gxrj.janitory.core.Dept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptService {
    
    @Autowired
    private DeptRepository repository;

    public Dept findByName( String name ) {
        return repository.findByName( name ).orElse( null );
    }
}
