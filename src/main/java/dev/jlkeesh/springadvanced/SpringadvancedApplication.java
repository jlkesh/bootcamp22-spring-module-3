package dev.jlkeesh.springadvanced;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jlkeesh.springadvanced.user.UserRepository;
import dev.jlkeesh.springadvanced.user.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@EnableAsync
@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
@EnableCaching
public class SpringadvancedApplication {
    private final UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(SpringadvancedApplication.class, args);
    }


    @Bean
    ApplicationRunner runner(ObjectMapper objectMapper) {
        return (args) -> {
            URL url = new URL("https://jsonplaceholder.typicode.com/users");
            List<Users> users = objectMapper.readValue(url, new TypeReference<>() {
            });
            userRepository.saveAll(users);
        };
    }

/*    @Scheduled(initialDelay = 5, fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    @CacheEvict(cacheNames = "users", allEntries = true)
    public void killUsersCacheEntries() {
        log.info("Killing All Entries Of Users Cache");
    }*/


}
