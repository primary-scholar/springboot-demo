package com.mimu.simple.java.algorithm.comm;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.*;


public class ClassicSortTest {

    /**
     * 冒泡排序
     *
     * @param unSortList
     */
    public void bubbleSort(Integer[] unSortList) {
        if (Objects.isNull(unSortList) || unSortList.length <= 1) {
            return;
        }
        for (int i = 0; i <= unSortList.length - 1; i++) {
            for (int j = 0; j < unSortList.length - 1 - i; j++) {
                // 相邻两位比较交换
                if (unSortList[j] > unSortList[j + 1]) {
                    swap(unSortList, j, j + 1);
                }
            }
        }
    }


    /**
     * 选择排序
     *
     * @param unSortList
     */
    public void selectSort(Integer[] unSortList) {
        if (Objects.isNull(unSortList) || unSortList.length <= 1) {
            return;
        }
        for (int i = 0; i <= unSortList.length - 1; i++) {
            Integer minIndex = i;
            for (int j = i + 1; j <= unSortList.length - 1; j++) {
                // 分成两个数据组前者有序，后者无序 在无序数组中选择最小的一个
                if (unSortList[minIndex] > unSortList[j]) {
                    minIndex = j;
                }
            }
            // 和有序组的最后一个交换
            swap(unSortList, i, minIndex);
        }
    }

    /**
     * 插入排序
     *
     * @param unSortList
     */
    public void insertSort(Integer[] unSortList) {
        if (Objects.isNull(unSortList) || unSortList.length <= 1) {
            return;
        }
        for (int i = 1; i <= unSortList.length - 1; i++) {
            // 分成两个数据组前者有序，后者无序 把后者中的第一个元素，和有序组中的元素交换排序，直到有序组中整体有序
            for (int j = i; j > 0; j--) {
                if (unSortList[j] < unSortList[j - 1]) {
                    swap(unSortList, j, j - 1);
                }
            }
        }

    }


    protected Integer[] initAList() {
        Integer length = (int) (Math.random() * 20) + 1;
        ArrayList<Integer> integers = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            int v = (int) (i * Math.random() * 100 - Math.random() * 100);
            integers.add(v);
        }
        Collections.shuffle(integers);
        return integers.toArray(new Integer[0]);
    }

    public Integer[] copyList(Integer[] origin) {
        return Arrays.copyOf(origin, origin.length);
    }

    @Test
    public void bubbleSortTest() {
        Integer loop = (int) (Math.random() * 50);
        for (int l = 0; l < loop; l++) {
            Integer[] origin = initAList();
            Integer[] copy = copyList(origin);
            bubbleSort(origin);
            Arrays.sort(copy);
            if (!Arrays.equals(origin, copy)) {
                System.out.println(JSONObject.toJSONString(origin));
                System.out.println(JSONObject.toJSONString(copy));
            }
        }
    }

    @Test
    public void selectSortTest() {
        Integer loop = (int) (Math.random() * 50);
        for (int l = 0; l < loop; l++) {
            Integer[] origin = initAList();
            Integer[] copy = copyList(origin);
            selectSort(origin);
            Arrays.sort(copy);
            if (!Arrays.equals(origin, copy)) {
                System.out.println(JSONObject.toJSONString(origin));
                System.out.println(JSONObject.toJSONString(copy));
            }
        }
    }

    @Test
    public void insertSortTest() {
        Integer loop = (int) (Math.random() * 50);
        for (int l = 0; l < loop; l++) {
            Integer[] origin = initAList();
            Integer[] copy = copyList(origin);
            insertSort(origin);
            Arrays.sort(copy);
            if (!Arrays.equals(origin, copy)) {
                System.out.println(JSONObject.toJSONString(origin));
                System.out.println(JSONObject.toJSONString(copy));
            }
        }
    }

    public void swap(Integer[] unSortList, int i, int j) {
        Integer tmp = unSortList[i];
        unSortList[i] = unSortList[j];
        unSortList[j] = tmp;
    }

}
