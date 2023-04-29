package dev.jlkeesh.springadvanced.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable Integer id) {
        return userService.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Integer id, @RequestBody UserUpdateDTO dto) {
        return userService.update(id, dto);
    }


}
