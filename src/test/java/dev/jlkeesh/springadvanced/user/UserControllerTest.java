package dev.jlkeesh.springadvanced.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*@SpringBootTest
@AutoConfigureMockMvc*/
@Slf4j
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @MockBean
    UserServiceImpl userServiceImpl;

    /*
    @MockBean
    UserRepository userRepository;
    */
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateMethodWithSuccessFullResponse() throws Exception {
        User user = User.builder()
                .email("choychi@gmail.com")
                .username("choychi")
                .password("123")
                .build();

        when(userServiceImpl.create(any())).thenReturn(user);
        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("asd"))
                .andReturn();

        String contentString = mvcResult.getResponse().getContentAsString();
        log.info(contentString);
        assertThat(contentString).containsIgnoringCase("choychi");
        verify(userServiceImpl, atLeastOnce()).create(any());
    }

    @Test
    void testCreateMethodWithfailure_400() throws Exception {
        User user = User.builder()
                .email("choychi@gmail.com")
                .username("choychi")
                .password("123")
                .build();

        MvcResult mvcResult = mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(new User()))
                )
                .andExpect(status().isBadRequest())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        log.info(contentAsString);
    }

    @Test
    void getUser() throws Exception {
        when(userServiceImpl.get(anyInt())).thenReturn(User.builder().id(1).build());
        MvcResult mvcResult = mockMvc.perform(get("/api/users/{userId}", 1)
                        .contentType("application/json"))
                .andExpect(status().is(200))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        User user = objectMapper.readValue(contentAsString, User.class);
        assertThat(user.getId()).isEqualTo(1);
        verify(userServiceImpl, atLeastOnce()).get(anyInt());

    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}