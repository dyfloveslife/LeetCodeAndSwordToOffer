package SwordToOfferSolution._63_MaximalProfit;

/*
 * 股票的最大利润
 *
 * 题目描述：
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖交易该股票可能获得的利润是多少？
 * 例如一只股票在某些时间节点的价格为{9, 11, 8, 5, 7, 12, 16, 14}。
 * 如果我们能在价格为5的时候买入并在价格为16时卖出，则能收获最大的利润11。
 *
 * 思路：
 * 1. 最大利润，即买入和卖出的最大差值，我们在最小买入价格的后面找到最大的卖出价格，这样差值就是最大的；
 * 2. 定义 f(i) 为当卖出价格为数组中第 i 个数字时可能得到的最大利润，则当卖出价格固定时，买入价格越低则获得的利润越大；
 * 3. 即当在遍历到数组中的第 i 个数字的时候，只要能记住之前的 i-1 个数字中最小值即可。
 */

public class Solution {
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int min = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            // 这里不断更新最小值，在遍历到第 i 个数的时候，需要将之前的最小值（min）与当前第 i 个数比较，取两者最小的
            min = Math.min(min, prices[i]);
            // 这里不断更新最大利润，由于之前已经在 prices[i] 和 min 中找到了最小值，则 prices[i]-min 一定是 ≥0 的
            // 由于这里的 prices[i] 代表当前最大卖出价格，所以减去之前的 min 就相当于找到了最大差值
            maxProfit = Math.max(maxProfit, prices[i] - min);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {9, 11, 8, 5, 7, 12, 16, 14};
        System.out.println(maxProfit(prices));
    }
}
