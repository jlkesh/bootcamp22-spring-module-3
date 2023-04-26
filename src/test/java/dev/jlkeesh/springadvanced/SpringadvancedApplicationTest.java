package dev.jlkeesh.springadvanced;

import dev.jlkeesh.springadvanced.user.UserRepository;
import dev.jlkeesh.springadvanced.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
public class SpringadvancedApplicationTest {

    @Autowired
    UserService userService;
    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }
}
