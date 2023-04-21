package dev.jlkeesh.springadvanced.cache;

import jakarta.annotation.PostConstruct;

import java.util.concurrent.ConcurrentHashMap;

public interface CacheManager {
    ConcurrentHashMap<String, Cache<?>> CACHES = new ConcurrentHashMap<>();
     void register();

    @SuppressWarnings("unchecked")
    default <T> Cache<T> getCache(Object o) {
        String cacheName = CacheUtils.getCacheName(o);
        Cache<?> cache = CACHES.get(cacheName);
        if (cache == null)
            throw new RuntimeException("Cache Not Found With NAME : " + cacheName);
        return (Cache<T>) cache;
    }


    default  <T> void putCache(String cacheName, Cache<T> cache) {
        CACHES.put(cacheName, cache);
    }
}
