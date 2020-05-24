package SwordToOfferSolution._63_MaximalProfit;

/*
 * 股票的最大利润
 *
 * 题目描述：
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖交易该股票可能获得的利润是多少？
 * 例如一只股票在某些时间节点的价格为 {9, 11, 8, 5, 7, 12, 16, 14}。
 * 如果我们能在价格为 5 的时候买入并在价格为 16 时卖出，则能收获最大的利润 11。
 *
 * 思路 1：暴力
 * 1. 找出两个数子的最大差值即可，但卖出价格必须大于买入价格；
 * 2. 将当前的买入价格记为 prices[i]，卖出价格为 prices[j]，并且 j>i；
 * 3. 每次更新最大利润 prices[j]-prices[i] 即可。
 *
 * 思路 2：DP
 * 1. 最大利润，即买入和卖出的最大差值，我们在最小买入价格的后面找到最大的卖出价格，这样差值就是最大的；
 * 2. 定义 dp[i] 为当卖出价格为数组中第 i 个数字时得到的最大利润，则当卖出价格固定时，买入价格越低则获得的利润越大；
 * 3. 即当在遍历到数组中的第 i 个数字的时候，只要能记住之前的 i-1 个数字中最小值即可。
 * 4. 转移方程为：dp[i] = max(dp[i-1], prices[i] - min(prices[0:i]))；
 * 5. 也就是在（前 i-1 日最大利润）与（第 i 日价格-前 i 日最低价格）之间取最大值。
 */
public class Solution {

    // 暴力
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int curProfit = prices[j] - prices[i];
                maxProfit = Math.max(maxProfit, curProfit);
            }
        }

        return maxProfit;
    }

    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            // 这里不断更新最小值，在遍历到第 i 个数的时候，需要将之前的最小值（min）与当前第 i 个数比较，取两者最小的
            if (prices[i] < min) {
                min = prices[i];
            }

            // 这里不断更新最大利润，由于之前已经在 prices[i] 和 min 中找到了最小值，则 prices[i]-min 一定是 ≥0 的
            // 由于这里的 prices[i] 代表当前最大卖出价格，所以减去之前的 min 就相当于找到了最大差值
            res = Math.max(res, prices[i] - min);
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prices1 = {9, 11, 8, 5, 7, 12, 16, 14};
        int[] prices2 = {7, 1, 5, 3, 6, 4};

        System.out.println(solution.maxProfit1(prices1));
        System.out.println(solution.maxProfit1(prices2));
        System.out.println("==");

        System.out.println(solution.maxProfit2(prices1));
        System.out.println(solution.maxProfit2(prices2));
    }
}
