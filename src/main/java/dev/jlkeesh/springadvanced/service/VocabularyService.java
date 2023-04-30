package dev.jlkeesh.springadvanced.service;

import dev.jlkeesh.springadvanced.dto.VocabularyCreateDTO;
import dev.jlkeesh.springadvanced.entities.Vocabulary;
import org.springframework.lang.NonNull;


public interface VocabularyService {
    Vocabulary create(@NonNull VocabularyCreateDTO dto);

    Vocabulary get(@NonNull String id);


}
