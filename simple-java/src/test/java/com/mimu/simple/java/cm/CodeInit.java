package com.mimu.simple.java.cm;

import org.junit.Test;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * author: mimu
 * date: 2019/9/3
 */
public class CodeInit {
    public static void main(String[] args) {
        System.out.println(Integer.toString(16, 16));
    }

    /**
     * attention the follow sout is different
     */
    @Test
    public void info() {
        BigDecimal divided = new BigDecimal(3);
        BigDecimal divisor = new BigDecimal(10);
        System.out.println(divided.divide(divisor, 2, RoundingMode.HALF_DOWN).floatValue() * 100);

        BigDecimal divided1 = new BigDecimal(3 * 100);
        BigDecimal divisor1 = new BigDecimal(10);
        System.out.println(divided1.divide(divisor1, 2, RoundingMode.HALF_DOWN).floatValue());
    }
}
