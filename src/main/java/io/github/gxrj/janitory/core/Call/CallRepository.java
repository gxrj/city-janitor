package io.github.gxrj.janitory.core.Call;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CallRepository extends JpaRepository<Call, UUID>{ }
