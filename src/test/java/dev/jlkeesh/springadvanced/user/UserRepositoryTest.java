package dev.jlkeesh.springadvanced.user;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
@ActiveProfiles("test")
/*@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:test-application.properties")*/
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void test_for_successful_save_method() {
        User user = User.builder()
                .username("javohir")
                .email("javohir@mail.ru")
                .otp("123")
                .password("123")
                .build();
        userRepository.save(user);
        assertEquals(1, user.getId());
    }

    @Test
    void test_for_validation_exception_save_method() {
        User user = User.builder()
/*                .username("javohir")
                .email("javohir@mail.ru")*/
                .otp("123")
                .password("123")
                .build();
        assertThrows(ConstraintViolationException.class, () -> userRepository.save(user))
                .printStackTrace();
    }

    @Test
    @Sql(scripts = "classpath:insert_data.sql")
    void test_for_multi_save() {
        long count = userRepository.count();
        assertEquals(10, count);
    }


}