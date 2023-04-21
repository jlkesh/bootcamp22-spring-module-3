package dev.jlkeesh.springadvanced.cache;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@ToString
public class Cache<T> {
    private int expiresIn;
    private final String name;
    private final ConcurrentHashMap<Object, CacheContent<T>> content;


    public Cache(String name) {
        this.name = name;
        this.content = new ConcurrentHashMap<>();
    }

    public Cache(String name, int expiresIn) {
        this.name = name;
        this.expiresIn = expiresIn;
        this.content = new ConcurrentHashMap<>();
    }


    public T get(Object key) {
        CacheContent<T> cacheContent = content.get(key);
        if (Objects.isNull(cacheContent))
            return null;
        else if (cacheContent.getExpiresAt().isBefore(LocalDateTime.now())) {
            content.remove(cacheContent);
            return null;
        }
        cacheContent.setExpiresAt(LocalDateTime.now().plusSeconds(expiresIn));
        System.out.println(cacheContent);
        return cacheContent.getBody();
    }

    public void put(Object key, T o) {
        CacheContent<T> cacheContent = new CacheContent<>(LocalDateTime.now().plusSeconds(expiresIn), o);
        System.out.println(cacheContent);
        content.put(key, cacheContent);
    }

    public void evict(Object key) {
        content.remove(key);
    }
}
