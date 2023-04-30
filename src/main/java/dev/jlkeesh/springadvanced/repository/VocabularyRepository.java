package dev.jlkeesh.springadvanced.repository;

import dev.jlkeesh.springadvanced.entities.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VocabularyRepository extends JpaRepository<Vocabulary, UUID> {
}