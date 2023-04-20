package dev.jlkeesh.springadvanced.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public final class VerificationMailSentEvent {
    private final String otp;
    private final String email;
}
