package dev.jlkeesh.springadvanced.user;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }


    public Users create(@NonNull Users user) {
        log.info("Saving Users : {}", user);
        return userRepository.save(user);
    }

    @Cacheable("users")
    public Users get(@NonNull Integer id) {
        log.info("Getting Users With ID : {}", id);
        Users user = userRepository
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
    public Users update(Integer id, UserUpdateDTO dto) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID : " + id));
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        return userRepository.save(user);
    }
    public List<Users> getAll() {
        return userRepository.findAll();
    }
}
