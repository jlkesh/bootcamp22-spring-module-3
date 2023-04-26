package dev.jlkeesh.springadvanced;

import dev.jlkeesh.springadvanced.user.User;
import dev.jlkeesh.springadvanced.user.UserRepository;
import dev.jlkeesh.springadvanced.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/*@SpringBootTest*/
@ExtendWith(MockitoExtension.class)
public class SpringadvancedApplicationTest {

    /*@Autowired*/
    UserService userService;
    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void userCreateTest() {
        User user = User.builder()
                .email("john@gmail.com")
                .username("john")
                .otp("1234")
                .password("213").build();
        when(userRepository.save(any())).thenReturn(User.builder().id(1).build());
        User actual = userService.create(user);
        assertThat(actual.getId()).isEqualTo(1);
        verify(userRepository, times(1)).save(any());
    }
}
