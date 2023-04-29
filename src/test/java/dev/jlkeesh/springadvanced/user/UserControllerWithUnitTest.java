package dev.jlkeesh.springadvanced.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/*@ExtendWith(MockitoExtension.class)*/
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerWithUnitTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @MockBean
    UserService userService;

    @BeforeEach
    void setUp() {
        /*userController = new UserController(userService);*/
    }

    @Test
    void create() {
        /*when(userService.create(any())).thenReturn(User.builder().id(1).build());
        User user = userController.create(new User());
        assertEquals(1, user.getId());
        verify(userService, atLeastOnce()).create(any());*/
    }


    @Test
    void testCreateApiForSuccessfulResponse() throws Exception {
        User user = User.builder()
                .email("choy@mail.ru")
                .otp("123")
                .password("123")
                .username("choy")
                .build();
        mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
        verify(userService, times(1)).create(any());


    }

    @Test
    void get() {
    }
}