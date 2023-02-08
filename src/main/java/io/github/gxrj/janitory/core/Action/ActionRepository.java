package io.github.gxrj.janitory.core.Action;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, UUID> { }
