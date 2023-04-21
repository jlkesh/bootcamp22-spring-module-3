package dev.jlkeesh.springadvanced.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class CacheManager {

    private final ConcurrentHashMap<String, Cache<?>> caches = new ConcurrentHashMap<>();

    public <T> Cache<T> getCache(Object o) {
        return CacheUtils.getCache(caches, o);
    }


    public <T> void putCache(String cacheName, Cache<T> cache) {
        caches.put(cacheName, cache);
    }


}
