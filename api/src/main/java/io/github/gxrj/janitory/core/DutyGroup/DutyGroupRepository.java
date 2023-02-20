package io.github.gxrj.janitory.core.DutyGroup;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DutyGroupRepository extends JpaRepository<DutyGroup, Long> { 
    
    Optional<DutyGroup> findByName( String name );
}