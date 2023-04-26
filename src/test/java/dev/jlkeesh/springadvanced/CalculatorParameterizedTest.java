package dev.jlkeesh.springadvanced;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorParameterizedTest {

    Calculator calculator;

    @BeforeEach
    void setUp() {
        this.calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
    }

    /*@ParameterizedTest
    @ValueSource(ints = {4, 5, 6})
    void add(int param) {
        assertEquals((param + param), calculator.add(param, param));
    }*/

    @ParameterizedTest
    @MethodSource("addSource")
    void add(CalculatorTestCase testCase) {
        assertEquals(testCase.expectedForAdd, calculator.add(testCase.a, testCase.b));
        assertEquals(testCase.expectedForDiv, calculator.div(testCase.a, testCase.b));
    }


    @ParameterizedTest(name = "{index} - {arguments}")
    @CsvSource(value = {
            "a , b , sum, div",
            "8, 4, 12, 2",
            "12, 4, 16, 3"
    }, useHeadersInDisplayName = true)
    void testWithCsvSource(int a, int b, int sum, int div) {
        assertEquals(sum, calculator.add(a, b));
        assertEquals(div, calculator.div(a, b));
    }
    @ParameterizedTest(name = "{index} - {arguments}")
    @CsvFileSource(resources = "/calc.csv", useHeadersInDisplayName = true)
    void testWithCsvFileSource(int a, int b, int sum, int div) {
        assertEquals(sum, calculator.add(a, b));
        assertEquals(div, calculator.div(a, b));
    }

    static Stream<CalculatorTestCase> addSource() {
        return Stream.of(
                new CalculatorTestCase(12, 3, 15, 4),
                new CalculatorTestCase(1, 4, 5, 0),
                new CalculatorTestCase(20, 5, 25, 4)
        );
    }

    public static class CalculatorTestCase {
        int a;
        int b;
        int expectedForAdd;
        int expectedForDiv;

        public CalculatorTestCase(int a, int b, int expectedForAdd, int expectedForDiv) {
            this.a = a;
            this.b = b;
            this.expectedForAdd = expectedForAdd;
            this.expectedForDiv = expectedForDiv;
        }

        @Override
        public String toString() {
            return "[a : %s, b : %s, sum : %s, div : %s ]"
                    .formatted(a, b, expectedForAdd, expectedForDiv);
        }
    }
   /* @Test
    void div() {
    }*/
}