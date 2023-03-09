package com.mimu.simple.java.algorithm.comm;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;


/**
 * 二分查找
 * 1. 在一个有序数组中查找 某个元素是否存在返回位置
 * 2. 在一个有序数组中查找，>=某个元素最左侧的位置
 * 3. 在一个有序数组中查找，<=某个元素最右侧的位置
 * 4. 在一个有序数组中查询，局部最小值
 */
public class BinarySearchTest extends ClassicSortTest {

    /**
     * 二分查找
     */
    public Integer binaryQuery(Integer[] sortList, Integer searchIng) {
        if (Objects.isNull(sortList) || sortList.length < 1) {
            return -1;
        }
        int left = 0, right = sortList.length;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (sortList[mid].equals(searchIng)) {
                return mid;
            }
            if (sortList[mid] < searchIng) {
                left = mid;
            }
            if (sortList[mid] > searchIng) {
                right = mid;
            }
        }
        return -1;
    }

    public Integer biggerThanOneLeftMostBinaryQuery(Integer[] sortList, Integer searchIng) {
        if (Objects.isNull(sortList) || sortList.length < 1) {
            return -1;
        }
        int left = 0, right = sortList.length, index = -1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (sortList[mid] >= searchIng) {
                index = mid;
                right = mid;
            }
            if (sortList[mid] < searchIng) {
                left = mid;
            }
            if (right - left == 1) {
                return index;
            }
        }
        return index;
    }

    public Integer smallThanOneRightMostBinaryQuery(Integer[] sortList, Integer searchIng) {
        if (Objects.isNull(sortList) || sortList.length < 1) {
            return -1;
        }
        int left = 0, right = sortList.length, index = -1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (sortList[mid] <= searchIng) {
                index = mid;
                right = mid;
            }
            if (sortList[mid] > searchIng) {
                left = mid;
            }
            if (right - left == 1) {
                return index;
            }
        }
        return index;
    }


    @Test
    public void binaryQueryTest() {
        System.out.println(JSONObject.toJSONString(sortList));
        Arrays.sort(sortList);
        System.out.println(JSONObject.toJSONString(sortList));
        int searching = sortList[(int) (Math.random() * 10)];
        System.out.println(searching);
        Integer integer = binaryQuery(sortList, searching);
        System.out.println(integer);
    }

    @Test
    public void biggerThanOneLeftMostBinaryQueryTest() {
        System.out.println(JSONObject.toJSONString(sortList));
        Arrays.sort(sortList);
        System.out.println(JSONObject.toJSONString(sortList));
        int searching = (int) (Math.random() * 100 - Math.random() * 100);
        System.out.println(searching);
        System.out.println(biggerThanOneLeftMostBinaryQuery(sortList, searching));
        Integer indexValue = sortList[(int) (Math.random() * 10)];
        System.out.println(indexValue);
        System.out.println(biggerThanOneLeftMostBinaryQuery(sortList, indexValue));
    }

    @Test
    public void smallThanOneRightMostBinaryQueryTest() {
        System.out.println(JSONObject.toJSONString(sortList));
        Arrays.sort(sortList);
        System.out.println(JSONObject.toJSONString(sortList));
        int searching = (int) (Math.random() * 100 - Math.random() * 100);
        System.out.println(searching);
        System.out.println(smallThanOneRightMostBinaryQuery(sortList, searching));
        Integer indexValue = sortList[(int) (Math.random() * 10)];
        System.out.println(indexValue);
        System.out.println(smallThanOneRightMostBinaryQuery(sortList, indexValue));
    }
}








