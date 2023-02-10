package io.github.gxrj.janitory.core.Address;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long > {
    
    Optional<District> findByName( String name );
}
