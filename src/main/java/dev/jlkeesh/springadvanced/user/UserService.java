package dev.jlkeesh.springadvanced.user;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import java.util.concurrent.TimeUnit;

@Slf4j
@CacheConfig(cacheNames = "users")
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User create(@NonNull User user) {
        log.info("Saving User : {}", user);
        return userRepository.save(user);
    }

    @Cacheable("users")
    public User get(@NonNull Integer id) {
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
        return user;
    }


    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public void update(Integer id, UserUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID : " + id));
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        userRepository.save(user);
    }
}
