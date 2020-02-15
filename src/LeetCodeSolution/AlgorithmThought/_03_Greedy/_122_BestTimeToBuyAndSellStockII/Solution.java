package LeetCodeSolution.AlgorithmThought._03_Greedy._122_BestTimeToBuyAndSellStockII;

/*
 * 买卖股票的最佳时机 II
 *
 * 题目描述：
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
 *
 * 思路：
 * 1. 上升的路径是利润，下降的路径是亏损；
 * 2. 从后一天往前一天看，如果后一天比前一天的股票价值高，则这两条算是一笔交易，则计入总利润；
 * 3. 即当后一天的价格大于前一天的价格就会产生利润，循环内计算的就是每次变化获取到的利润。
 */
public class Solution {
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            // 相邻两天的收益
            int cur_profit = prices[i] - prices[i - 1];
            if (cur_profit > 0) {
                res += cur_profit;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(arr));
    }
}
