package dev.jlkeesh.springadvanced.user;

import dev.jlkeesh.springadvanced.cache.CacheManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CacheManager cacheManager;


    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Integer id) {
        return userService.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody UserUpdateDTO dto) {
        userService.update(id, dto);
    }


    @GetMapping("/monitor-cache")
    public CacheManager cacheManager() {
        return cacheManager;
    }
}
