package dev.jlkeesh.springadvanced.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/*@ExtendWith(MockitoExtension.class)*/
@WebMvcTest(controllers = UserController.class)
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
        when(userService.create(any())).thenReturn(User.builder().id(1).build());
        User user = User.builder()
                /*.email("choy@mail.ru")*/
                .otp("123")
                .password("123")
                .username("choy")
                .build();

        mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user))
        ).andExpect(status().isCreated());
        verify(userService, times(1)).create(any());
    }

    @Test
    void testCreateApiForBadRequestResponse() throws Exception {
        User user = User.builder()
                /*.email("choy@mail.ru")*/
                .otp("123")
                .password("123")
                .username("choy")
                .build();
        when(userService.create(any())).thenReturn(User.builder().id(1).build());

        mockMvc.perform(post("/api/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void testCreateApiForSuccessAndCheckResponse() throws Exception {
        User user = User.builder()
                .email("choy@mail.ru")
                .otp("123")
                .password("123")
                .username("choy")
                .build();
        when(userService.create(any())).thenReturn(User.builder().id(1).build());


        mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user))
                )
                .andExpect(jsonPath("$.id").hasJsonPath())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.email").hasJsonPath())

                .andExpect(status().isCreated());
    }


    @Test
    void testCreateApiForSuccessAndCheckResponse2() throws Exception {
        User user = User.builder()
                .email("choy@mail.ru")
                .otp("123")
                .password("123")
                .username("choy")
                .build();
        when(userService.create(any())).thenReturn(User.builder().id(1).build());
        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user))
                ).andExpect(status().isCreated())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        User user1 = objectMapper.readValue(contentAsString, User.class);
        assertEquals(1, user1.getId());
    }

    @Test
    void testCreateApiForBadRequest() throws Exception {
        User user = User.builder()
                /*.email("choy@mail.ru")*/
                .otp("123")
                .password("123")
                /*.username("choy")*/
                .build();
        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user))
                ).andExpect(status().isBadRequest())
                /*.andExpect(jsonPath("$.errorMessage").hasJsonPath())
                .andExpect(jsonPath("$.errorMessage.email").value("email can not be blank"))
                .andExpect(jsonPath("$.errorMessage.username").value("username can not be blank"))
                .andExpect(jsonPath("$.errorPath").value("/api/users"));*/
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        Map<String, Object> errorDTO = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });
        @SuppressWarnings("unchecked")
        Map<String, String> errorMessage = (Map<String, String>) errorDTO.get("errorMessage");
        assertEquals("email can not be blank", errorMessage.get("email"));
    }

    @Test
    void get() {
    }
}