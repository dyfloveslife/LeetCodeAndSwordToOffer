package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._300_LongestIncreasingSubsequence;

import java.util.Arrays;

/*
 * 最长递增子序列
 *
 * 题目描述：
 * 给你一个整数数组 nums，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如 [3, 6, 2, 7] 是数组 [0, 3, 1, 6, 2, 2, 7] 的子序列。
 *
 * 思路一：动态规划
 * 1. 使用 dp[i] 代表以 nums[i] 结尾的最长子序列长度；
 * 2. 设 j 位于 [0, i) 之间，当 nums[i] > nums[j] 时，说明找到了一个严格上升的元素，则此时 dp[j] + 1；
 * 3. 状态转移方程为 dp[i] = max(dp[i], dp[j] + 1)；
 * 4. 对于初始化 dp 数组，其中每个元素的值都为 1，因为每个元素它自己就可以看作是一个子序列；
 * 5. 时间复杂度 O(n^2)，空间复杂度 O(n)。
 */
public class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length, ans = 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 符合严格递增的子序列条件
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};

        Solution solution = new Solution();
        System.out.println(solution.lengthOfLIS(nums1)); // 4
        System.out.println(solution.lengthOfLIS(nums2)); // 4
        System.out.println(solution.lengthOfLIS(nums3)); // 1
    }
}
