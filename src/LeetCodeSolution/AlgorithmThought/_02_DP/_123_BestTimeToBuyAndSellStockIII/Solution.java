package LeetCodeSolution.AlgorithmThought._02_DP._123_BestTimeToBuyAndSellStockIII;

/*
 * 买股票的最佳时机 Ⅲ
 *
 * 题目描述：
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。
 * 你最多可以完成两笔交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例：
 * 输入: [3, 3, 5, 0, 0, 3, 1, 4]
 * 输出: 6
 * 解释：买入 0 卖出 3，买入 1 卖出 4，得到利润为 6。
 *
 * 思路：
 * 1. 核心思想：买入股票就扣钱，卖出股票就加钱；
 * 2. 因此按照流程“买入->卖出->买入->卖出”，最后求得第二次卖出的最大值即可；
 */
public class Solution {
    public int maxProfit(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int b1 = Integer.MIN_VALUE;
        int b2 = Integer.MIN_VALUE;
        int s1 = 0;
        int s2 = 0;

        for (int i = 0; i < nums.length; i++) {
            // 第一次买入的时候，是亏钱的
            b1 = Math.max(b1, -nums[i]);
            // 第一次卖出的时候，是赚钱的
            s1 = Math.max(s1, b1 + nums[i]);
            // 第二次买入的时候，是在第一次 s1 的基础上减钱的
            b2 = Math.max(b2, s1 - nums[i]);
            // 第二次卖出的时候，是赚钱的
            s2 = Math.max(s2, b2 + nums[i]);
        }
        return s2;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3, 3, 5, 0, 0, 3, 1, 4};

        System.out.println(solution.maxProfit(nums));
    }
}
