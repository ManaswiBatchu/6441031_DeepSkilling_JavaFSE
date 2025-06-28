package com.example;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
        System.out.println("Setup: Calculator instance created.");
    }

    @After
    public void tearDown() {
        calculator = null;
        System.out.println("Teardown: Calculator instance destroyed.");
    }

    @Test
    public void testAdd() {
        int result = calculator.add(5, 2);

        assertEquals(7, result);
    }

    @Test
    public void testMultiply() {
        int result = calculator.multiply(10, 2);

        assertEquals(20, result);
    }
}
