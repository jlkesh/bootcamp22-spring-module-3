package dev.jlkeesh.springadvanced.utils;

import dev.jlkeesh.springadvanced.user.User;
import dev.jlkeesh.springadvanced.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenGenerator {
    private final UserRepository userRepository;

    public void generateToken(User user) {
        user.setOtp(UUID.randomUUID().toString());
        userRepository.save(user);
    }
}
