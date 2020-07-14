package LeetCodeSolution.AlgorithmThought._02_DP._322_CoinChange;

import java.util.Arrays;

/*
 * 零钱兑换
 *
 * 题目描述：
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 思路：
 * 1. 整体流程是使用一维 dp 数组进行实现，不断地填充数组；
 * 2. 具体流程：https://www.youtube.com/watch?v=jgiZlGzXMBw；
 * 3. 画图理解。
 */
public class Solution {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }

        // 0~11
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        // i 表示 dp 数组的索引，我们需要做的就是不断地填充 dp 数组
        for (int i = 1; i <= amount; i++) {
            // 每次获取每个硬币
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        // 需要考虑不可能达到金额的情况
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] coins = {1, 2, 5};
        int amount = 11;

        System.out.println(solution.coinChange(coins, amount));
    }
}
