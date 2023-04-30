package dev.jlkeesh.springadvanced.dto;

import dev.jlkeesh.springadvanced.entities.Vocabulary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link Vocabulary} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VocabularyCreateDTO implements Serializable {
    private String word;
    private String definition;
    private String examples;
}