package io.github.gxrj.janitory.core.Action;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, UUID> {

    Optional<Action> findByProtocol( String protocol ); 
    List<Action> findByAgent_Login( String agentLogin );
    List<Action> findByCall_Protocol( String callProtocol );


    List<Action> findByCreationDateBetweenAndfindByAgent_Login( 
                                      LocalDateTime start, LocalDateTime end, String agentLogin );
    List<Action> findByCreationDateBetweenAndfindByAgent_Dept_Name( 
                                      LocalDateTime start, LocalDateTime end, String agentDeptName );

}
