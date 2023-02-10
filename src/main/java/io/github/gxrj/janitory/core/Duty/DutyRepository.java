package io.github.gxrj.janitory.core.Duty;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DutyRepository extends JpaRepository<Duty, Long> {

    Optional<Duty> findByName( String name );
    List<Duty> findByDept_Name( String deptName );
    List<Duty> findByGroup_Name( String groupName );
}