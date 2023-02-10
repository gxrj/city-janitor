package io.github.gxrj.janitory.core.Action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionService {
    
    @Autowired
    private ActionRepository repository;

    public void createOrUpdate( Action entity ) {
        repository.save( entity );
    }

    public List<Action> listByCall( String callProtocol ) {
        return repository.findByCall_Protocol( callProtocol );
    }
}
