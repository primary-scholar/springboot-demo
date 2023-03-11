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
 * <p>
 * 二分法 适用于 每次决策时 能够舍弃一半的候选数据 的场景
 * 不仅仅适用于 有序数据中
 */
public class BinarySearchTest extends ClassicSortTest {

    /**
     * 二分查找
     */
    public Integer binaryQuery(Integer[] sortList, Integer searchIng) {
        if (Objects.isNull(sortList) || sortList.length < 1) {
            return -1;
        }
        int left = 0, right = sortList.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (sortList[mid].equals(searchIng)) {
                return mid;
            }
            if (sortList[mid] < searchIng) {
                left = mid + 1;
            }
            if (sortList[mid] > searchIng) {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * >= 某个元素最左侧的位置
     *
     * @param sortList
     * @param searchIng
     * @return
     */
    public Integer biggerThanOneLeftMostBinaryQuery(Integer[] sortList, Integer searchIng) {
        if (Objects.isNull(sortList) || sortList.length < 1) {
            return -1;
        }
        int left = 0, right = sortList.length - 1, index = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (sortList[mid] >= searchIng) {
                index = mid;
                right = mid - 1;
            }
            if (sortList[mid] < searchIng) {
                left = mid + 1;
            }
        }
        return index;
    }

    /**
     * <= 某个元素最右侧的位置
     *
     * @param sortList
     * @param searchIng
     * @return
     */
    public Integer smallThanOneRightMostBinaryQuery(Integer[] sortList, Integer searchIng) {
        if (Objects.isNull(sortList) || sortList.length < 1) {
            return -1;
        }
        int left = 0, right = sortList.length - 1, index = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (sortList[mid] <= searchIng) {
                index = mid;
                left = mid + 1;
            }
            if (sortList[mid] > searchIng) {
                right = mid - 1;
            }
        }
        return index;
    }


    @Test
    public void binaryQueryTest() {
        Integer loop = (int) (Math.random() * 50);
        for (int l = 0; l < loop; l++) {
            Integer[] origin = initAList();
            Arrays.sort(origin);
            int index = ((int) (Math.random() * 100) % origin.length);
            Integer searchIng = origin[index];
            Integer queryIndex = binaryQuery(origin, searchIng);
            if (!searchIng.equals(origin[queryIndex])) {
                String info = "searchIng=%s,searchIng index=%s while queryIng=%s";
                System.out.println(JSONObject.toJSONString(origin));
                System.out.println(String.format(info, searchIng, index, queryIndex));
                return;
            }
            searchIng = (int) (Math.random() * 100 - Math.random() * 100);
            queryIndex = binaryQuery(origin, searchIng);
            if (!Arrays.asList(origin).contains(searchIng) && queryIndex != -1) {
                String info = "searchIng=%s,searchIng index=%s while queryIng=%s";
                System.out.println(JSONObject.toJSONString(origin));
                System.out.println(String.format(info, searchIng, -1, queryIndex));
                return;
            }
        }
    }

    @Test
    public void biggerThanOneLeftMostBinaryQueryTest() {
        Integer loop = (int) (Math.random() * 50);
        for (int l = 0; l < loop; l++) {
            Integer[] origin = initAList();
            Arrays.sort(origin);
            int index = ((int) (Math.random() * 100) % origin.length);
            Integer searchIng = origin[index];
            Integer queryIndex = biggerThanOneLeftMostBinaryQuery(origin, searchIng);
            if (origin[queryIndex] < searchIng) {
                String info = "searchIng=%s,searchIng index=%s while queryIng=%s";
                System.out.println(JSONObject.toJSONString(origin));
                System.out.println(String.format(info, searchIng, index, queryIndex));
                return;
            }
        }
    }

    @Test
    public void smallThanOneRightMostBinaryQueryTest() {
        Integer loop = (int) (Math.random() * 50);
        for (int l = 0; l < loop; l++) {
            Integer[] origin = initAList();
            Arrays.sort(origin);
            int index = ((int) (Math.random() * 100) % origin.length);
            Integer searchIng = origin[index];
            Integer queryIndex = smallThanOneRightMostBinaryQuery(origin, searchIng);
            if (origin[queryIndex] > searchIng) {
                String info = "searchIng=%s,searchIng index=%s while queryIng=%s";
                System.out.println(JSONObject.toJSONString(origin));
                System.out.println(String.format(info, searchIng, index, queryIndex));
                return;
            }
        }
    }
}








