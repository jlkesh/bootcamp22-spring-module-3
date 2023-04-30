package dev.jlkeesh.springadvanced.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public double add(double a, double b) {
        return a + b;
    }

    public double divide(double a, double b) {
        return a / b;
    }
}
