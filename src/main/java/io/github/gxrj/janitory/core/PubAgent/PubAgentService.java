package io.github.gxrj.janitory.core.PubAgent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PubAgentService {
    
    @Autowired
    private PubAgentRepository repository;

    public PubAgent findByLogin( String login ) {
        return repository.findByLogin( login ).orElse( null );
    } 

    public void createOrUpdate( PubAgent entity ) {
        repository.saveAndFlush( entity );
    }

    public List<PubAgent> listAll() {
        return repository.findAll();
    }

    public List<PubAgent> listByDeptName( String deptName ) {
        return repository.findByDept_name( deptName );
    }

    public void changePassword( PubAgent entity, String oldPassword, String newPassword ) {
        // To Do Later
    }
}
