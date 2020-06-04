package LeetCodeSolution.AlgorithmThought._02_DP._188_BestTimeToBuyAndSellStockIV;

import java.util.Arrays;

/*
 * 买卖股票的最佳时机 Ⅳ
 *
 * 题目描述：
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 思路：
 * 1. 对于 k 大于等于数组长度一半的时候，可以直接使用买股票的最佳时机 II，即贪心的方式；
 * 2. 对于其它的 k 的情况，可以考虑使用买股票的最佳时机 III 的解法；
 * 3. 但该题与 III 唯一的区别在于：III 的做法是在第一次买入之前是没有卖出操作的；
 * 4. 因此，可以创建两个长度为 k+1 的数组，用 sell[0] 的位置作为前提。
 */
public class Solution {
    public int maxProfit(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return 0;
        }

        int len = nums.length;
        // 当 k 大于数组长度一般的时候，此问题退化成买股票的最佳时机 II
        // 即尽可能地完成更多的交易
        if (k >= len / 2) {
            return process(nums);
        }

        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);

        for (int num : nums) {
            for (int i = 1; i <= k; i++) {
                buy[i] = Math.max(buy[i], sell[i - 1] - num);
                sell[i] = Math.max(sell[i], buy[i] + num);
            }
        }
        return sell[k];
    }

    private int process(int[] nums) {
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if (diff > 0) {
                res += diff;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {3, 2, 6, 5, 0, 3};
        int[] nums2 = {2, 4, 1};
        int[] nums3 = {3, 3, 5, 0, 0, 3, 1, 4};
        int[] nums4 = {1, 2, 4, 2, 5, 7, 2, 4, 9, 0};

        int k1 = 2;
        int k2 = 2;
        int k3 = 2;
        int k4 = 4;

        System.out.println(solution.maxProfit(nums1, k1));
        System.out.println(solution.maxProfit(nums2, k2));
        System.out.println(solution.maxProfit(nums3, k3));
        System.out.println(solution.maxProfit(nums4, k4));
    }
}
