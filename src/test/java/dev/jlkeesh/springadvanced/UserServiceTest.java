package dev.jlkeesh.springadvanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    UserService userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void create() {
        when(userRepository.save(any(User.class))).thenReturn(new User(1));
        /*doReturn(new User(1)).when(userRepository.save(any(User.class)));*/
        User actual = userService.create(new User("adash", "adash@gmail.com", "adash123"));
        assertEquals(1, actual.getId());
    }

    @Test
    void testForMockingException() {
        when(userRepository.save(null))
                .thenThrow(new RuntimeException("persistent object can not be null.........."));
        assertThrows(RuntimeException.class, () -> userService.create(null))
                .printStackTrace();
        verify(userRepository, atLeastOnce()).save(null);
    }

    @Test
    void alreadyTakenEmailTest() {
        User user = new User("choyuz", "choyuz@gmail.com", "choyuz123");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User()));
        assertThrows(RuntimeException.class, () -> userService.create(user));

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(new User(1));
        User actualUser = userService.create(new User("choyuz2", "choyuz2@gmail.com", "choyuz123"));
        assertEquals(1, actualUser.getId());

        verify(userRepository, atLeast(2)).findByEmail(anyString());
        verify(userRepository, atLeastOnce()).save(any());
    }


}