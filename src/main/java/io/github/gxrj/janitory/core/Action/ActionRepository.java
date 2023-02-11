package io.github.gxrj.janitory.core.Action;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActionRepository extends JpaRepository<Action, UUID> {

    Optional<Action> findByProtocol( String protocol ); 
    List<Action> findByAgent_Login( String agentLogin );
    List<Action> findByCall_Protocol( String callProtocol );

    @Query( "select a from Action where a.creationDate between ?1 and ?2 and a.agent.login = ?3" )
    List<Action> searchByAgentBetween( LocalDateTime start, LocalDateTime end, String agentLogin );

    @Query( "select a from Action where a.creationDate between ?1 and ?2 and a.call.destination.name = ?3" )
    List<Action> searchDeptBetween( LocalDateTime start, LocalDateTime end, String agentDeptName );

}
