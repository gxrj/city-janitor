package io.github.gxrj.janitory.core.PubAgent;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PubAgentRepository extends JpaRepository<PubAgent, UUID> { 

    Optional<PubAgent> findByLogin( String login );
    List<PubAgent> findByDept_name( String name );
}