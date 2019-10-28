package com.mimu.simple.java.leetcode;

/**
 * author: mimu
 * date: 2019/9/30
 */

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * Two Sum
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class LC1Test {

    @Test
    public void deal() {
        int[] origin = new int[]{2, 7, 11, 12};
        int[] ints = caculate2Map(origin, 14);
        int[] ints1 = caculateOBO(origin, 9);
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
        System.out.println();
        for (int i : ints1) {
            System.out.print(i + " ");
        }
    }

    /*
    使用穷举法，遍历数据，复杂度O(n^2)
     */
    public int[] caculateOBO(int[] origin, int target) {
        int[] result = new int[2];
        if (origin == null || origin.length < 2) {
            return result;
        }
        for (int start = 0; start < origin.length; start++) {
            for (int end = origin.length - 1; end > start; end--) {
                if (origin[start] + origin[end] == target) {
                    result[0] = start;
                    result[1] = end;
                }
            }
        }
        return result;
    }

    /*
    把数组放到 map中遍历一遍数组，复杂度O(n)
     */
    public int[] caculate2Map(int[] origin, int target) {
        int[] result = new int[2];
        if (origin == null || origin.length < 2) {
            return result;
        }
        Map<Integer, Integer> convet2Map = new HashMap<>();
        for (int start = 0; start < origin.length; start++) {
            convet2Map.put(origin[start], start);
            int other = target - origin[start];
            if (convet2Map.get(other) != null && convet2Map.get(other) != start) {
                result[0] = convet2Map.get(other);
                result[1] = start;
            }
        }
        return result;
    }
}
