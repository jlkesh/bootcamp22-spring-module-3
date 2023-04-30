package dev.jlkeesh.springadvanced.service;

import dev.jlkeesh.springadvanced.config.SessionUser;
import dev.jlkeesh.springadvanced.dto.VocabularyCreateDTO;
import dev.jlkeesh.springadvanced.entities.Vocabulary;
import dev.jlkeesh.springadvanced.repository.VocabularyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class VocabularyServiceImpl implements VocabularyService {
    private final VocabularyRepository budgetRepository;
    private final SessionUser sessionUser;

    @Override
    public Vocabulary create(@NonNull VocabularyCreateDTO dto) {
        var budget = Vocabulary.builder()
                .word(dto.getWord())
                .definition(dto.getDefinition())
                .examples(dto.getExamples())
                .build();
        return budgetRepository.save(budget);
    }

    @Override
    public Vocabulary get(@NonNull String id) {
        return budgetRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Vocabulary Not Found"));
    }

    @ShellMethodAvailability({"vocc"})
    public Availability checkForLogin() {
        return Objects.nonNull(sessionUser.getId()) ?
                Availability.available() :
                Availability.unavailable("Please Login First");
    }
}
