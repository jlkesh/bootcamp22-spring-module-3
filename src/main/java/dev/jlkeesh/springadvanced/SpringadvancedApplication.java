package dev.jlkeesh.springadvanced;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
public class SpringadvancedApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringadvancedApplication.class, args);
    }



}
