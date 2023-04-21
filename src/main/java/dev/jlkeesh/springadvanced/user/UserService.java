package dev.jlkeesh.springadvanced.user;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ConcurrentMapCacheManager concurrentMapCacheManager;
    private final Cache cache;

    public UserService(UserRepository userRepository,
                       ConcurrentMapCacheManager concurrentMapCacheManager) {
        this.userRepository = userRepository;
        this.concurrentMapCacheManager = concurrentMapCacheManager;
        this.cache = concurrentMapCacheManager.getCache("users");
    }


    public User create(@NonNull User user) {
        log.info("Saving User : {}", user);
        return userRepository.save(user);
    }

    public User get(@NonNull Integer id) {
        User cachedUser = cache.get(id, User.class);
        if (cachedUser != null)
            return cachedUser;

        log.info("Getting User With ID : {}", id);
        User user = userRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception ignored) {
        }
        log.info("Returning User : {}", user);
        cache.put(user.getId(), user);
        return user;
    }


    public void delete(Integer id) {
        userRepository.deleteById(id);
        cache.evict(id);
    }

    public void update(Integer id, UserUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID : " + id));
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        userRepository.save(user);
        cache.put(id, user);
    }
}
