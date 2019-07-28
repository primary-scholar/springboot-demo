package com.mimu.simple.sj.cm;

import org.junit.Test;

import java.util.BitSet;

/**
 * author: mimu
 * date: 2019/7/28
 */
public class BitSetTest {

    @Test
    public void info(){
        BitSet set = new BitSet();
        System.out.println(set.size());
        set.set(196,false);
        set.set(100);
        System.out.println(set.size());
        System.out.println(set);
    }
}
