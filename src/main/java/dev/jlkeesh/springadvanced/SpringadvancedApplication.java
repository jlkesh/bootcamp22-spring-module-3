package dev.jlkeesh.springadvanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringadvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringadvancedApplication.class, args);
    }


}
