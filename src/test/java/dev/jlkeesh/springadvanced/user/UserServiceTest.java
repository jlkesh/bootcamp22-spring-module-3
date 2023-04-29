package dev.jlkeesh.springadvanced.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
/*        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);*/
    }

    @Test
    void create() {
        /*User user = User.builder().id(1).build();
        when(userRepository.save(any())).thenReturn(user);
        User actual = userService.create(new User());
        assertEquals(1, actual.getId());
        verify(userRepository, times(1)).save(any());*/
    }

    @Test
    @Disabled
    void createWithSpringBootTest() {
        User user = User.builder()
                .email("bro@gmail.com")
                .username("bro")
                .password("123")
                .otp("fn29p4owjrgo3ughrfndoi3hglrkfwd")
                .build();
        User actual = userService.create(user);
        assertEquals(1, actual.getId());
    }

    @Test
    void createWithSpringBootTest_mockBean() {
        when(userRepository.save(any())).thenReturn(User.builder().id(1).build());
        User user = User.builder()
                .email("bro@gmail.com")
                .username("bro")
                .password("123")
                .otp("fn29p4owjrgo3ughrfndoi3hglrkfwd")
                .build();
        User actual = userService.create(user);
        assertEquals(1, actual.getId());
    }

    @Test
    void get() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}