package io.github.gxrj.janitory.core.Action;

import io.github.gxrj.janitory.core.Call.Call;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, UUID> {

    Optional<Action> findByProtocol( String protocol ); 
    List<Action> findByAgent_Login( String agentLogin );
    List<Action> findByCall( Call call );
}
