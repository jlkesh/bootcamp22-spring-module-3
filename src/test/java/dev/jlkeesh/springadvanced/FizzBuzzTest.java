package dev.jlkeesh.springadvanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzTest {
    FizzBuzz fizzBuzz;

    @BeforeEach
    void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @Test
    void shouldFailOnNull() {
        assertEquals("1", fizzBuzz.convert(1));
    }

    @Test
    void testForDifferentNumberResults() {
        assertEquals("2", fizzBuzz.convert(2));
        assertEquals("4", fizzBuzz.convert(4));
        assertEquals("11", fizzBuzz.convert(11));
    }

    @Test
    void should_fail_on_3() {
        assertEquals("Fizz", fizzBuzz.convert(3));
        assertEquals("Fizz", fizzBuzz.convert(6));
        assertEquals("Fizz", fizzBuzz.convert(9));
    }

    @Test
    void should_fail_on_5() {
        assertEquals("Buzz", fizzBuzz.convert(5));
    }

    @Test
    void should_fail_on_15() {
        assertEquals("FizzBuzz", fizzBuzz.convert(15));
    }

    @ParameterizedTest(name = "{displayName} => {index} :> {arguments}")
    @DisplayName("Test With 10 000 number")
    @CsvFileSource(resources = "/fizzbuzz.csv", useHeadersInDisplayName = true)
    void testWith_10_000_Numbers(int number, String expected) {
        assertEquals(expected, fizzBuzz.convert(number));
    }

}
