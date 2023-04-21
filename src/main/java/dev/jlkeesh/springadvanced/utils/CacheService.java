package dev.jlkeesh.springadvanced.utils;

import dev.jlkeesh.springadvanced.user.User;
import dev.jlkeesh.springadvanced.user.UserUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {

    private ConcurrentHashMap<Integer, User> cache = new ConcurrentHashMap<>();

    public User get(Integer key) {
        return cache.get(key);
    }

    public void put(User user) {
        cache.put(user.getId(), user);
    }

    public void evict(Integer key) {
        cache.remove(key);
    }

    public void put(Integer id, UserUpdateDTO dto) {
        User user = cache.get(id);
        if (user != null) {
            user.setEmail(dto.getEmail());
            user.setUsername(dto.getUsername());
        }
    }
}
