package LeetCodeSolution.AlgorithmThought._01_DoublePointer._16_3SumClosest;

import java.util.Arrays;

/*
 * 最接近的三数之和
 *
 * 题目描述：
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。
 * 假定每组输入只存在唯一答案。
 *
 * 思路：
 * 1. 与 15 题类似，只不过这道题需要找到最接近 target 的和；
 * 2. 还是先排序，然后使用双指针的方式不停地找到最接近 target 的三个数的和。
 */
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null) {
            return 0;
        }
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                // 核心
                if (Math.abs(target - sum) < Math.abs(target - res)) {
                    res = sum;
                } else if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-1, 2, 1, -4};
        int target = 1;

        System.out.println(solution.threeSumClosest(nums, target));
    }
}
