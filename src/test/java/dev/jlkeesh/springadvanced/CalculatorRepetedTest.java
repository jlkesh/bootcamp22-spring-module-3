package dev.jlkeesh.springadvanced;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorRepetedTest {

    Calculator calculator;
    Random random;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        random = new Random();
    }

    @AfterEach
    void tearDown() {
    }

    @RepeatedTest(10)
    void add() {
        int a = random.nextInt(1, 100);
        int b = random.nextInt(1, 100);
        int sum = a + b;
        System.out.println(a + " + " + b + " = " + sum);
        assertEquals(sum, calculator.add(a, b));
    }

    @RepeatedTest(value = 10,name = "Testing div method {currentRepetition} of {totalRepetitions} ")
    void div() {
        int a = random.nextInt(60, 100);
        int b = random.nextInt(10, 30);
        int result = a / b;
        System.out.println(a + " / " + b + " = " + result);
        assertEquals(result, calculator.div(a, b));
    }
}