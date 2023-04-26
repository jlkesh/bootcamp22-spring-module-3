package dev.jlkeesh.springadvanced;

import dev.jlkeesh.springadvanced.user.User;
import dev.jlkeesh.springadvanced.user.UserRepository;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringadvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringadvancedApplication.class, args);
    }


   /*
   @Bean
    ApplicationRunner runner() {
        return (args) -> {
            userRepository.save(User.builder()
                    .email("Elshod@mail.ru")
                    .username("elshod")
                    .password("q123")
                    .otp("2q4rfldjvnskf.d")
                    .build());
        };
    }
    */

}
