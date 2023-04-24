package dev.jlkeesh.springadvanced;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.awt.*;
import java.time.Duration;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

// @DisplayName("Calculator Class Methods Test")
@TestMethodOrder(MethodOrderer.MethodName.class)
@IndicativeSentencesGeneration(separator = "=>", generator = DisplayNameGenerator.ReplaceUnderscores.class)
public class SimpleTest {

    Calculator calculator;

    @BeforeAll
    static void setUpAll() {
        System.out.println("SET UP ALL");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Calculator Object is created");
        calculator = new Calculator();
    }


    @Test
    @Order(1)
        // @DisplayName("Add Method Test")
    void calculator_add_method_test_should_pass() {
        int result = calculator.add(12, 90);
        assertEquals(102, result, "Method Should Return 102 but returned " + result);
    }

    @Test
        // @DisplayName("Div Method Test(Should Be Success)")
    void calculator_div_method_test_should_pass() {
        int result = calculator.div(12, 4);
        assertEquals(3, result);
    }

    @Test
    // @DisplayName("Div Method Test(Should Be Success)")
    //@Disabled("Disable because this method throws timeout exception")
    @DisabledIf(value = "condition", disabledReason = "Because Of Because")
    void calculator_div_method_test_should_not_pass_for_timeout() {
        assertTimeout(Duration.ofSeconds(1), () -> calculator.div(12, 4));
    }

    @Test
        // @DisplayName("Div Method Test(Should Fail)")
    void calculator_div_method_test_should_not_pass() {
        String message = assertThrows(ArithmeticException.class, () -> calculator.div(12, 0))
                .getMessage();
        System.err.println("Error : " + message);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Cleaning........");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("........Cleaning ALL........");
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void runIsOSMacOS() {

    }


    @Test
    @EnabledOnOs(OS.LINUX)
    void runIsOSLinux() {

    }

    @Test
    @DisabledOnOs(OS.LINUX)
    void disableIdOsIsLinux() {

    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_15, max = JRE.JAVA_19)
    void enableIfJreIs17() {

    }


    boolean condition() {
        return true;
    }

}
