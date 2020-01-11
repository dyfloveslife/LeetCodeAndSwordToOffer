package SwordToOfferSolution._10_Fibonacci;

/*
 * 斐波那契数列
 *
 * 题目描述：
 * 写一个函数，输入 n，求斐波那契（Fibonacci）数列的第 n 项。
 *
 * 思路：
 * 1. 递归；
 * 2. 带有备忘录的递归；
 * 3. 递归改 DP；
 * 4. 改进的 DP。
 */
public class Solution {
    public int fibpnacci(int n) {
        if (n < 2) {
            return n;
        }

        int pre2 = 0;
        int pre1 = 1;
        int fib = 0;
        for (int i = 2; i <= n; i++) {
            fib = pre2 + pre1;
            pre2 = pre1;
            pre1 = fib;
        }
        return fib;
    }
}
