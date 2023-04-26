package dev.jlkeesh.springadvanced;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class UserRepository {
    private static final Logger log = Logger.getLogger(UserRepository.class.getName());
    private static final List<User> users = new ArrayList<>();
    private final AtomicInteger generator = new AtomicInteger(3);

    static {
        users.add(new User(1, "john", "john@gmail.com", "john123"));
        users.add(new User(2, "choyuz", "choyuz@gmail.com", "choyuz123"));
    }

    public User save(User user) {
        log.info("User save method : " + user);
        if (user == null)
            throw new RuntimeException("Persistent object can not be null");

        user.setId(generator.getAndIncrement());
        users.add(user);
        return user;
    }

    public Optional<User> findByEmail(String email) {
        for (User user : users)
            if (user.getEmail().equals(email))
                return Optional.of(user);
        return Optional.empty();
    }
}
