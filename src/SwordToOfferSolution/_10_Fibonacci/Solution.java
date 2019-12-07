package SwordToOfferSolution._10_Fibonacci;

/*
 * 斐波那契数列
 * 改进的 DP
 */
public class Solution {
    public int Fibpnacci(int n) {
        if (n < 2) return n;
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
