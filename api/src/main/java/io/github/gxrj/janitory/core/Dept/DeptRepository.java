package io.github.gxrj.janitory.core.Dept;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<Dept, Long> { 

    Optional<Dept> findByName( String name );
}
