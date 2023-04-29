package dev.jlkeesh.springadvanced;

import dev.jlkeesh.springadvanced.user.UserRepository;
import dev.jlkeesh.springadvanced.user.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
public class SpringadvancedApplicationTest {

    @Autowired
    UserServiceImpl userServiceImpl;
    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userServiceImpl = new UserServiceImpl(userRepository);
    }
}
