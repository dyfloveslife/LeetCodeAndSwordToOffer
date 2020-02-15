package LeetCodeSolution.AlgorithmThought._03_Greedy._121_BestTimeToBuyAndSellStock;

/*
 * 买卖股票的最佳时机
 *
 * 题目描述：
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * 注意你不能在买入股票前卖出股票。
 *
 * 思路：
 * 1. 可以使用 DP，维护一个最小值和一个最大利润，最大利润为当前元素减去最小值；
 * 2. 即找到最小的谷后面的最大的峰。
 */
public class Solution {
    public static int maxProfit(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            maxprofit = Math.max(maxprofit, arr[i] - min);
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        int[] arr = {7, 6, 4, 3, 1};
        System.out.println(maxProfit(arr));
    }
}
