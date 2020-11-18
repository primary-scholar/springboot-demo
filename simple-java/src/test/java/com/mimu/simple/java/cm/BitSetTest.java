package com.mimu.simple.java.cm;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import javax.xml.transform.Source;
import java.math.BigDecimal;
import java.util.BitSet;

/**
 * author: mimu
 * date: 2019/7/28
 */
public class BitSetTest {

    @Test
    public void info() {
        BitSet set = new BitSet();
        System.out.println(set.size());
        set.set(1);
        set.set(196);
        set.set(100000);
        System.out.println(set.size());
        System.out.println(set.get(1000));
        System.out.println(set.get(196));
        System.out.println(set.get(1));
        System.out.println(set.get(2));
    }

    @Test
    public void info1() {
        String ip = ".2.131.80";
        System.out.println(getPointIndex(ip, 1));
        System.out.println(getPointIndex(ip, 2));
        System.out.println(getPointIndex(ip, 3));
        System.out.println(getPointIndex(ip, 4));
        System.out.println(getPointIndex(ip, 5));
    }

    private int getPointIndex(String origin, int ordinal) {
        if (StringUtils.isEmpty(origin) || ordinal <= 0) {
            return 0;
        }
        int index = 0;
        for (int i = 0; i < origin.length(); i++) {
            char c = origin.charAt(i);
            if (c == '.') {
                index++;
            }
            if (index >= ordinal) {
                return i;
            }
        }
        return 0;
    }

}
