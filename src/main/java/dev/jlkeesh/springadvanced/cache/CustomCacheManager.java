package dev.jlkeesh.springadvanced.cache;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

//@Component
public class CustomCacheManager implements CacheManager {


    @Override
    @PostConstruct
    public void register() {
        putCache("users", new Cache<>("users", 200));
    }
}

