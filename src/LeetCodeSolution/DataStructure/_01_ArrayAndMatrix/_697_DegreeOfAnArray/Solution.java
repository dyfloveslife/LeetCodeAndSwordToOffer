package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._697_DegreeOfAnArray;

import java.util.HashMap;

/*
 * 数组的度
 *
 * 题目描述：
 * 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 *
 * 思路：
 * 1. 这里使用三个 map 来存储需要用到的信息；
 * 2. map1 存储当前元素及当前元素出现的次数；
 * 3. map2 存储当前元素及当前元素首次出现的位置；
 * 4. map3 存储当前元素及当前元素首次和末次出现位置的长度；
 * 5. 然后在 map1 中统计数组最大的度；
 * 6. 如果最大的度与当前元素出现的次数相同，则开始通过 map3 统计最短的连续子数组的长度。
 */
public class Solution {
    public int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 当数组中只有一个元素的时候，它的度就是 1，那么最终的结果也就是 1
        if (nums.length == 1) {
            return 1;
        }

        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        HashMap<Integer, Integer> map3 = new HashMap<>();

        // 该 for 循环用于填充三个 map
        for (int i = 0; i < nums.length; i++) {
            map1.put(nums[i], map1.getOrDefault(nums[i], 0) + 1);
            if (!map2.containsKey(nums[i])) {
                map2.put(nums[i], i);
            }
            map3.put(nums[i], i - map2.get(nums[i]) + 1);
        }

        // 在 map1 中，每个元素出现次数的最大值，就是数组中最大的度
        int maxCount = 0;
        for (int value : map1.values()) {
            maxCount = Math.max(maxCount, value);
        }

        int res = Integer.MAX_VALUE;
        for (int key : map1.keySet()) {
            // 如果当前元素所出现的次数等于数组中最大的度的话，那么就开始统计最短的连续子数组的长度，
            // 而其长度是保存在 map3 中的
            if (maxCount == map1.get(key)) {
                res = Math.min(res, map3.get(key));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 2, 3, 1};
        int[] nums2 = {1, 2, 2, 3, 1, 4, 2};

        System.out.println(solution.findShortestSubArray(nums1));
        System.out.println(solution.findShortestSubArray(nums2));
    }
}
