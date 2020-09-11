package LeetCodeSolution.AlgorithmThought._02_DP._121_BestTimeToBuyAndSellStock;

/*
 * 买股票的最佳时机
 *
 * 题目描述：
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 *
 * 示例：
 * 输入: [7, 1, 5, 3, 6, 4]
 * 输出: 5
 * 解释：买入 1，卖出 6，利润为 5。
 *
 * 思路：
 * 1. 遍历一次数组即可类似于将数组中的每个数描绘在坐标轴中，然后找到最低的峰谷，以及最高的峰谷，然后再求差；
 * 2. 使用 DP 的话，定义 dp[i] 表示前 i 天的最大利润，则有 dp[i] = max(dp[i-1], nums[i]-minPrices)。
 */
public class Solution {
    public int maxProfit1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = 0;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            // 这个 if 就是找到当前的最小值，然后使用当前的值减去这个最小值，
            // 从而不断更新 res，得到最终的答案
            if (num < min) {
                min = num;
            }
            res = Math.max(res, num - min);
        }
        return res;
    }

    public int maxProfit2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int[] dp = new int[nums.length];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
            dp[i] = Math.max(dp[i - 1], nums[i] - min);
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {7, 1, 5, 3, 6, 4};

        System.out.println(solution.maxProfit1(nums));
        System.out.println(solution.maxProfit2(nums));
    }
}