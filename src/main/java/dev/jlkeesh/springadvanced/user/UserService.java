package dev.jlkeesh.springadvanced.user;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final CacheManager cacheManager;

    public UserService(UserRepository userRepository,
                       CacheManager cacheManager) {
        this.userRepository = userRepository;
        this.cacheManager = cacheManager;
    }


    public User create(@NonNull User user) {
        log.info("Saving User : {}", user);
        return userRepository.save(user);
    }

    @Cacheable(cacheNames = {"users"}, key = "#id")
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


    @CacheEvict(cacheNames = "users", key = "#id")
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @CachePut(cacheNames = "users", key = "#result.id" /*, condition = "#id eq 12", unless = "#id eq 12"*/)
    public User update(Integer id, UserUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID : " + id));
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        return userRepository.save(user);
    }
}
