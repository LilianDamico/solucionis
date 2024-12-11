package com.solucionis.infrastructure.persistence.repositories;

import com.solucionis.domain.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
