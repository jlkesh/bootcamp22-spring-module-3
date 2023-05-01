package dev.jlkeesh.springadvanced;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableScheduling
public class SpringadvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringadvancedApplication.class, args);
    }

}

@Component
class CustomPromptProvider implements PromptProvider {
    @Override
    public AttributedString getPrompt() {
        return new AttributedString("bootcamp-shell" + "-->", AttributedStyle.BOLD.background(AttributedStyle.CYAN));
    }
}