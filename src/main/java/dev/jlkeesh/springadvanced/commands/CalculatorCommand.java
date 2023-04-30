package dev.jlkeesh.springadvanced.commands;

import dev.jlkeesh.springadvanced.annotation.NotEquals;
import dev.jlkeesh.springadvanced.service.CalculatorService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.shell.Availability;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.ParameterValidationException;
import org.springframework.shell.command.CommandHandlingResult;
import org.springframework.shell.command.annotation.ExceptionResolver;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.StringJoiner;

@ShellComponent
@RequiredArgsConstructor
public class CalculatorCommand {
    private boolean connected;

    private final CalculatorService calculatorService;

    @ShellMethod(value = "use to add two numbers", prefix = "-")
    public double add(@ShellOption(value = "-a") double numberOne,
                      @ShellOption(value = "-b") double numberTwo) {
        return calculatorService.add(numberOne, numberTwo);
    }

    @ShellMethod(value = "use to sum 4 numbers", key = "c_sum")
    public double sum(@ShellOption(arity = -1) double[] numbers) {
        return Arrays.stream(numbers).sum();
    }

    @ShellMethod(value = "use to divide 2 numbers", key = "c_div")
    public double divide(@ShellOption(value = "-a") double a,
                         @NotEquals(value = 0)
                         @ShellOption(value = "-b") double b) {
        return calculatorService.divide(a, b);
    }

    @ExceptionResolver({ParameterValidationException.class})
    CommandHandlingResult errorHandler(ParameterValidationException e) {
        Set<ConstraintViolation<Object>> constraintViolations = e.getConstraintViolations();
        StringJoiner sj = new StringJoiner("\n", "", "\n");
        for (ConstraintViolation<Object> violation : constraintViolations) {
            String message = violation.getMessage();
            String argument = violation.getPropertyPath().toString();
            sj.add(argument + " : " + message);
        }
        return CommandHandlingResult.of(sj.toString(), 400);
    }

    @ShellMethod
    public String logout() {
        return "Successfully signed out";
    }


    @ExceptionResolver(CommandNotCurrentlyAvailable.class)
    CommandHandlingResult handleCommandNotAvailable(CommandNotCurrentlyAvailable e) {
        String message = e.getMessage();
        return CommandHandlingResult.of(message, 401);
    }

    public Availability logoutAvailability() {
        return connected ? Availability.available() : Availability.unavailable("Sign in first");
    }

}
