package dev.jlkeesh.springadvanced;

import dev.jlkeesh.springadvanced.config.SessionUser;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

import java.util.Optional;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class SpringadvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringadvancedApplication.class, args);
    }


    @Bean
    public AuditorAware<String> auditorAware(SessionUser sessionUser) {
        return () -> Optional.of(sessionUser.getId());
    }

}

@Component
class CustomPromptProvider implements PromptProvider {
    @Override
    public AttributedString getPrompt() {
        return new AttributedString("bootcamp-shell" + "-->", AttributedStyle.DEFAULT.background(AttributedStyle.CYAN));
    }
}

