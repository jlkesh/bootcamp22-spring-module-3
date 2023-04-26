package dev.jlkeesh.springadvanced;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User create(User user) {
        String email = user.getEmail();
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent())
            throw new RuntimeException("Email already taken: " + email);
        return userRepository.save(user);
    }

}
