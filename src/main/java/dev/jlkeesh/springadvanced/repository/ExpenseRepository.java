package dev.jlkeesh.springadvanced.repository;

import dev.jlkeesh.springadvanced.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
}