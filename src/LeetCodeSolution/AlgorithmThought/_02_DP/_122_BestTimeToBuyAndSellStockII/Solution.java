package LeetCodeSolution.AlgorithmThought._02_DP._122_BestTimeToBuyAndSellStockII;

/*
 * 买股票的最佳时机 Ⅱ
 *
 * 题目描述：
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。
 * 你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 思路：
 * 1. 其实只要当天的价格比前一天的价格大的话，就可以直接将利润加在一起；
 * 2. 例如 [7, 1, 5, 6]，可以买 1 卖 6，得到的利润是 5，但是为什么不买 1 卖 5，再买 5 卖 6 呢？
 * 3. 其实这两种方式得到的利润是一样的，题目要求可以在当天卖出后再接着当天买入；
 * 4. 使用 dp 的话，定义 dp[i][j] 表示第 i 天是否持有股票，j=0 表示不持有，j=1 表示持有；
 * 5. 假如今天没有股票：
 *        昨天也没有股票，今天不做任何操作；
 *        昨天有股票，今天按照价格卖掉了，收获了一笔钱；
 *    假如今天持有一支股票：
 *        昨天我手里就有这支股票，今天不做任何操作；
 *        昨天我手里没有股票，今天按照价格买入了一支股票，花掉了一笔钱。
 */
public class Solution {
    public int maxProfit1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if (diff > 0) {
                res += diff;
            }
        }
        return res;
    }

    public int maxProfit2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[][] dp = new int[nums.length][2];
        dp[0][0] = 0;
        dp[0][1] = -nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + nums[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - nums[i]);
        }
        return dp[nums.length - 1][0];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {7, 1, 5, 3, 6, 4};

        System.out.println(solution.maxProfit1(nums));
        System.out.println(solution.maxProfit2(nums));
    }
}
