package dev.jlkeesh.springadvanced.utils;

import dev.jlkeesh.springadvanced.user.Users;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.springframework.stereotype.Component;

@Slf4j
public class CacheLogger implements CacheEventListener<Long, Users> {
    @Override
    public void onEvent(CacheEvent<? extends Long, ? extends Users> event) {
        log.info("Key: {} | EventType: {} | Old value: {} | New value: {}",
                event.getKey(),
                event.getType(),
                event.getOldValue(),
                event.getNewValue());
    }

}
