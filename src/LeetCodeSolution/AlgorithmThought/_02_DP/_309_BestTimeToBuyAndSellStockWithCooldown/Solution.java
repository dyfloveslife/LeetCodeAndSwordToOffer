package LeetCodeSolution.AlgorithmThought._02_DP._309_BestTimeToBuyAndSellStockWithCooldown;

/*
 * 最佳买卖股票时机含冷冻期
 *
 * 题目描述：
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格。
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在下一天买入股票 (即冷冻期为 1 天)。
 *
 * 示例：
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为 [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * 思路：
 * 1. dp[i] 表示第 i 天获得的股票最大利润是多少；
 * 2. 但是对于该题来说，dp[i][0] 表示当天不持有股票，dp[i][1] 表示当前持有股票，我们需要取两者的最大值；
 * 3. 对于当天不持有股票 dp[i][0] 来说，它有两种情况造成不持有股票的情况：
 *    3.1) 在当天有股票的情况下卖出了股票，即 dp[i][0]=dp[i-1][1]+price[i]，也就是昨天的利润加上今天卖的价格；
 *    3.2) 昨天本来就没有，当天啥也没有，也就是本来就不持有股票，即 dp[i][0]=dp[i-1][0]。
 *    3.3) 因此，对于当天不持有股票来说，就是取上面两者的最大值；
 * 4. 对于当天持有股票 dp[i][1] 来说，也有两种情况：
 *    4.1) 当天本来没有股票的，然后我当天买了股票，即 dp[i][1]=dp[i-2][0]-price[i]；
           由于存在冷冻期，dp[i-2][0] 表示前天没有股票的利润；
 *    4.2) 还有一种情况是，昨天就有的，那么今天肯定也不能买，因此今天的利润一定是昨天的利润，即 dp[i][1]=dp[i-1][1];
 * 5. 初始值 dp[0][0]=0，第一天不持有股票，也没卖出股票，因此第一天的利润就是 0；
 * 6. 初始值 dp[0][1]=0-price[0]，第一天就持有股票，那么利润肯定是负值，因为是你自己花钱买的，因此就是负的。
 */
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        int[][] dp = new int[n][2];
        // 当前不持有股票
        dp[0][0] = 0;
        // 当前持有股票
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], ((i - 2) >= 0 ? dp[i - 2][0] : 0) - prices[i]);
        }

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        // 截至第 i 天，最后一个操作是卖时的最大收益
        int[] sell = new int[len];
        // 截至第 i 天，最后一个操作是买时的最大收益
        int[] buy = new int[len];
        // 截至第 i 天，最后一个操作是冷冻期时的最大收益
        int[] cool = new int[len];
        buy[0] = -prices[0];
        for (int i = 1; i < len; i++) {
            // 如果第 i 天是卖的话，那么就等于昨天的收益+今天卖出的股票
            sell[i] = buy[i - 1] + prices[i];
            // 如果第 i 天是买的话，那么如果第 i 天被冷冻，那么就等于昨天的收益
            // 如果第 i 天是买的话，那么如果第 i 天没有被冷冻，那么就等于昨天的收益再减去今天买入的
            buy[i] = Math.max(buy[i - 1], cool[i - 1] - prices[i]);
            cool[i] = Math.max(sell[i - 1], cool[i - 1]);
        }
        return Math.max(sell[len - 1], cool[len - 1]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prices = {1, 2, 3, 0, 2};

        System.out.println(solution.maxProfit(prices));
        System.out.println(solution.maxProfit2(prices));
    }
}
