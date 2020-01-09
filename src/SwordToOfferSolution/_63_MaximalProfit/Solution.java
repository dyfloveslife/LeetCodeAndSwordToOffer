package SwordToOfferSolution._63_MaximalProfit;

/*
 * 股票的最大利润
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
            maxProfit = Math.max(maxProfit, prices[i] - min);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {9, 11, 8, 5, 7, 12, 16, 14};
        System.out.println(maxProfit(prices));
    }
}
