package com.ls.webflux_demo;

import com.ls.webflux_demo.util.Calculator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/3 21:47
 */
public class CalculatorTest {
    @Test
    public void testCalculator() {
        Calculator calculator = Mockito.mock(Calculator.class);
        Mockito.when(calculator.add(2, 3)).thenReturn(5);

        int result = calculator.add(2, 3);
        assertEquals(5, result);
    }
}