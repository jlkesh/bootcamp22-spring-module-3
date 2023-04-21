package dev.jlkeesh.springadvanced;

import dev.jlkeesh.springadvanced.utils.CacheService;
import dev.jlkeesh.springadvanced.utils.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

@EnableAsync
@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
public class SpringadvancedApplication {
    private final CacheService cacheService;
    private final MailService mailService;

    public static void main(String[] args) {
        SpringApplication.run(SpringadvancedApplication.class, args);
    }

    @Scheduled(initialDelay = 6, fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void runEvery10Seconds() {
        if (mailService.getStatus()) {
            cacheService.getCache().forEach((key, model) -> {
                System.out.println("SENDING .......... " + key + " : " + model);
                cacheService.getCache().remove(key);
            });
        }
    }


}
