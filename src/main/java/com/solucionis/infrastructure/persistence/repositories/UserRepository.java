package com.solucionis.infrastructure.persistence.repositories;

import com.solucionis.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByCpf(String cpf);
}
