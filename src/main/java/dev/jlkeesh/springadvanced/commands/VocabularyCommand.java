package dev.jlkeesh.springadvanced.commands;

import dev.jlkeesh.springadvanced.dto.VocabularyCreateDTO;
import dev.jlkeesh.springadvanced.entities.Vocabulary;
import dev.jlkeesh.springadvanced.service.VocabularyService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDate;

@ShellComponent
@RequiredArgsConstructor
public class VocabularyCommand {

    private final VocabularyService budgetService;

    @ShellMethod(value = "use to create vocabulary", key = "vocc")
    public Vocabulary createVocabulary(@ShellOption(value = "-n", help = "hello") String word,
                          @ShellOption(value = "-d", help = "greeting") String definition,
                          @ShellOption(value = "-e", help = "hi John | Hello how are ?") String examples) {
        var dto = new VocabularyCreateDTO(word, definition, examples);
        return budgetService.create(dto);
    }

}
