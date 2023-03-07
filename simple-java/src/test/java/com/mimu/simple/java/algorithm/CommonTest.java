package com.mimu.simple.java.algorithm;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.tools.ant.taskdefs.Sleep;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.net.Inet4Address;
import java.util.*;


public class CommonTest {

    private Integer[] sortList;

    @Before
    public void prepareList() {
        ArrayList<Integer> integers = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            integers.add(i);
        }
        Collections.shuffle(integers);
        sortList = integers.toArray(new Integer[integers.size()]);
    }

    @Test
    public void bubbleSortTest() {
        System.out.println(JSONObject.toJSONString(sortList));
        bubbleSort(sortList);
        System.out.println(JSONObject.toJSONString(sortList));
    }

    @Test
    public void selectSortTest() {
        System.out.println(JSONObject.toJSONString(sortList));
        selectSort(sortList);
        System.out.println(JSONObject.toJSONString(sortList));
    }

    @Test
    public void insertSortTest() {
        System.out.println(JSONObject.toJSONString(sortList));
        insertSort(sortList);
        System.out.println(JSONObject.toJSONString(sortList));
    }

    public void swap(Integer[] unSortList, int i, int j) {
        Integer tmp = unSortList[i];
        unSortList[i] = unSortList[j];
        unSortList[j] = tmp;
    }

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


}
