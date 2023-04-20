package dev.jlkeesh.springadvanced.events;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UserCreatedEvent {
    private final Integer userId;
    public Integer getUserId() {
        return userId;
    }
}
