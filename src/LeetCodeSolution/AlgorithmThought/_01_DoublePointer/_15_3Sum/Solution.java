package LeetCodeSolution.AlgorithmThought._01_DoublePointer._15_3Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 三数之和
 *
 * 题目描述：
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 思路：
 * 1. 可以使用双指针的方式，首先对数组进行排序；
 * 2. 首先固定第一个元素，然后左指针指向剩下元素的左元素，右指针指向剩下元素的右元素；
 * 3. 如果被固定的这个元素已经大于 0 了，由于已经排好序了，后面不可能再有三数之和等于 0 的，所以三数之和一定是大于 0 的，
 *    因此需要直接退出即可；
 * 4. 对重复的元素直接跳过，避免出现重复的结果集；
 * 5. 将这三个元素进行相加，得到 sum；
 * 6. 如果 sum == 0，则执行循环，开始生成结果，并判断左边界和右边界是否和它们的下一位重复，如果重复，则跳过；
 * 7. 如果 sum > 0，则右边界需要往左移动；
 * 8. 如果 sun < 0，则左边界需要往右移动。
 */
public class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // 不符合题意
            if (nums[i] > 0) {
                break;
            }
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-1, 0, 1, 2, -1, -4};

        System.out.println(solution.threeSum(nums));
    }
}
