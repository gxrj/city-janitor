package io.github.gxrj.janitory.core.Action;

import java.time.LocalDateTime;
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

    public List<Action> listByAuthor( String login ) {
        return repository.findByAgent_Login( login );
    }

    public List<Action> listByCall( String callProtocol ) {
        return repository.findByCall_Protocol( callProtocol );
    }

    public Action findByProtocol( String protocol ) {
        return repository.findByProtocol( protocol ).orElse( null );
    }

    public List<Action> listIntervalByDept( LocalDateTime start, LocalDateTime end, String deptName ) {
        return repository.searchByDeptBetween( start, end, deptName );
    }

    public List<Action> listIntervalByAgent( LocalDateTime start, LocalDateTime end, String agentLogin ) {
        return repository.searchByAgentBetween( start, end, agentLogin );
    }
}
