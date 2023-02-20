package io.github.gxrj.janitory.core.Citizen;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepository extends JpaRepository<Citizen, UUID> {
    Optional<Citizen> findByEmail( String email );
}
