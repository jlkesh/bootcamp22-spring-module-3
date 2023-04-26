package dev.jlkeesh.springadvanced;

import java.util.concurrent.TimeUnit;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int div(int a, int b) {
        if (b != 0){
            try {
                TimeUnit.MILLISECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return a / b;
    }

    public static boolean cond(){
        return true;
    }
}
