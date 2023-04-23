package dev.jlkeesh.springadvanced;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jlkeesh.springadvanced.user.UserRepository;
import dev.jlkeesh.springadvanced.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.net.URL;
import java.util.List;

@EnableAsync
@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
@EnableCaching
public class SpringadvancedApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringadvancedApplication.class, args);
    }


    @Bean
    ApplicationRunner runner(@Lazy UserRepository userRepository, ObjectMapper objectMapper) {
        return (args) -> {
            var url = new URL("https://jsonplaceholder.typicode.com/users");
            List<Users> users = objectMapper.readValue(url, new TypeReference<List<Users>>() {
            });
            userRepository.saveAll(users);
        };
    }


    @Bean
    public CacheManager cacheManager() throws Exception {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        return cachingProvider.getCacheManager(new ClassPathResource("classpath:ehcache.xml").getURI(), getClass().getClassLoader());
    }


}
