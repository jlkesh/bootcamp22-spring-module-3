package dev.jlkeesh.springadvanced.utils;

import dev.jlkeesh.springadvanced.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {
    private final CacheService cacheService;
    private boolean on = true;

    public void sendEmail(Map<String, Object> model) {
        if (on) {
            log.info("Verification Email Sent to : {}", model);
        } else {
            log.info("Cached Info : {}", model);
            cacheService.put(model.get("id"), model);
        }
    }


    public boolean turnOnOrTurnOffSMTPServer() {
        this.on = !this.on;
        return this.on;
    }

    public boolean getStatus() {
        return on;
    }
}
