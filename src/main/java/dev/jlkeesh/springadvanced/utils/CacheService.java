package dev.jlkeesh.springadvanced.utils;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {

    @Getter
    private final ConcurrentHashMap<Object, Object> cache = new ConcurrentHashMap<>();


    public Object get(Object key) {
        return cache.get(key);
    }

    public Object put(Object key, Map<String, Object> model) {
        System.out.println(cache);
        Object put =  cache.put(key, model);
        return put;
    }


}
