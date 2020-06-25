package LeetCodeSolution.DataStructure._04_HashTable._1_TwoSum;

import java.util.Arrays;
import java.util.HashMap;

/*
 * 两数之和
 *
 * 题目描述：
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。
 * 但是，数组中同一个元素不能使用两遍。
 *
 * 思路：
 * 1. 使用 HashMap，key 存储当前元素，value 存储当前元素的索引；
 * 2. 如果 map 中存在 diff=target-当前元素，当前元素的索引以及 diff 所对应的索引进行返回；
 * 3. 如果 map 中不存在 diff=target-当前元素，则将当前元素以及当前元素对应的索引存储到 map 中。
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                return new int[]{map.get(diff), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    public int[] twoSum1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int[] arr = new int[nums.length];
        System.arraycopy(nums, 0, arr, 0, nums.length);

        Arrays.sort(nums);

        int i = 0;
        int j = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                i = left;
                j = right;
                break;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        int k;
        int[] res = new int[2];
        // 在排序数组与未排序数组中找对应元素的下标
        for (k = 0; k < nums.length; k++) {
            if (nums[i] == arr[k]) {
                res[0] = k;
                break;
            }
        }
        for (int l = 0; l < nums.length; l++) {
            // 其中 l != k 表示避免重复
            if (nums[j] == arr[l] && l != k) {
                res[1] = l;
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        System.out.println(Arrays.toString(solution.twoSum(nums, target)));
        System.out.println(Arrays.toString(solution.twoSum1(nums, target)));
    }
}
