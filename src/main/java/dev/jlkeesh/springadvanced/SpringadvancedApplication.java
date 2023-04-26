package dev.jlkeesh.springadvanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringadvancedApplication {
/*    private final UserRepository userRepository;*/


    public static void main(String[] args) {
        SpringApplication.run(SpringadvancedApplication.class, args);
    }


    /*@Bean
    ApplicationRunner runner() {
        return (args) -> {
            userRepository.save(User.builder().email("Elshod@mail.ru").username("elshod")
                    .password("q123")
                    .otp("2q4rfldjvnskf.d")
                    .build());
        };
    }*/

}
