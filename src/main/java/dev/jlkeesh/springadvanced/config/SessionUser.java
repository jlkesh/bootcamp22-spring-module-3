package dev.jlkeesh.springadvanced.config;

import org.springframework.stereotype.Component;

@Component
public class SessionUser {
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
