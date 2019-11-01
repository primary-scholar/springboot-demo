package com.mimu.simple.java.utils.collection;

import org.junit.Test;

import java.util.LinkedHashSet;


/**
 author: mimu
 date: 2019/11/1
 */
public class CollectionSetTest {

    @Test
    public void linkedHashSetInfo(){
        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>();
        hashSet.add(null);
        hashSet.add(13);
        hashSet.add(10);
        hashSet.add(14);
        hashSet.add(13);
        hashSet.add(null);
        System.out.println(hashSet);

        hashSet.remove(13);

    }

}
