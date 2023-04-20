package dev.jlkeesh.springadvanced.user;

import dev.jlkeesh.springadvanced.events.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher publisher;

    @PostMapping
    @Transactional
    public User create(@RequestBody UserCreateDto dto) {
        User user = userMapper.toEntity(dto);
        userRepository.save(user);
        publisher.publishEvent(new UserCreatedEvent(user.getId()));
        return user;
    }
}
