package dev.jlkeesh.springadvanced.user;

import dev.jlkeesh.springadvanced.utils.CacheService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CacheService cacheService;


    public User create(@NonNull User user) {
        log.info("Saving User : {}", user);
        return userRepository.save(user);
    }

    public User get(@NonNull Integer id) {
        User cachedUser = cacheService.get(id);
        if (cachedUser != null)
            return cachedUser;

        log.info("Getting User With ID : {}", id);
        User user = userRepository
                .findById(id)
                .orElse(new User());// 2s
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception ignored) {
        }
        log.info("Returning User : {}", user);
        cacheService.put(user);
        return user;
    }


    public void delete(Integer id) {
        userRepository.deleteById(id);
        cacheService.evict(id);
    }

    public void update(Integer id, UserUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID : " + id));
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        userRepository.save(user);
        cacheService.put(id, dto);
    }
}
