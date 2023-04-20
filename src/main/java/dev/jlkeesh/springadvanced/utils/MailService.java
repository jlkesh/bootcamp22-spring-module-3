package dev.jlkeesh.springadvanced.utils;

import dev.jlkeesh.springadvanced.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {
    private final UserRepository userRepository;

    public void sendEmail(Map<String, Object> model) {
        log.info("Verification Email Sent to : {}", model);
        throw new RuntimeException("Exception For Exception");
    }
}
