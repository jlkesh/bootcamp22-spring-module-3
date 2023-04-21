package dev.jlkeesh.springadvanced.user;

import dev.jlkeesh.springadvanced.events.UserCreatedEvent;
import dev.jlkeesh.springadvanced.utils.CacheService;
import dev.jlkeesh.springadvanced.utils.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher publisher;
    private final MailService mailService;
    private final CacheService cacheService;


    @PostMapping
    @Transactional
    public User create(@RequestBody UserCreateDto dto) {
        User user = userMapper.toEntity(dto);
        userRepository.save(user);
        publisher.publishEvent(new UserCreatedEvent(user.getId()));
        return user;
    }


    @GetMapping("turnOnOrTurnOffSMTPServer")
    public ResponseEntity<Boolean> turnOnOrTurnOffSMTPServer() {
        return ResponseEntity.ok(mailService.turnOnOrTurnOffSMTPServer());
    }

    @GetMapping("/view-cache")
    public ResponseEntity<ConcurrentHashMap<Object, Object>> viewCache() {
        log.info("Cache  : {}", cacheService.getCache());
        return ResponseEntity.ok(cacheService.getCache());
    }


}
