package dev.jlkeesh.springadvanced.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    public Users create(@RequestBody Users user) {
        return userService.create(user);
    }

    @GetMapping("/{id}")
    public Users get(@PathVariable Integer id) {
        return userService.get(id);
    }

    @GetMapping("/test/{id}")
    public Users getTest(@PathVariable Integer id) {
        return userService.get(id);
    }

    @GetMapping
    public List<Users> getAll() {
        return userService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public Users update(@PathVariable Integer id, @RequestBody UserUpdateDTO dto) {
        return userService.update(id, dto);
    }


}
