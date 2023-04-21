package dev.jlkeesh.springadvanced;

import dev.jlkeesh.springadvanced.user.User;
import dev.jlkeesh.springadvanced.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@Slf4j
@EnableAsync
@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
public class SpringadvancedApplication {
    private final UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(SpringadvancedApplication.class, args);
    }


    @Bean
    ApplicationRunner runner() {
        return (args) -> {
            userRepository.save(User.builder().email("Elshod@mail.ru").username("elshod")
                    .password("q123")
                    .otp("2q4rfldjvnskf.d")
                    .build());
        };
    }

    @Bean
    public ConcurrentMapCacheManager concurrentMapCacheManager(){
        ConcurrentMapCacheManager concurrentMapCacheManager = new ConcurrentMapCacheManager();
        concurrentMapCacheManager.setCacheNames(List.of("users"));
        return concurrentMapCacheManager;
    }


}
