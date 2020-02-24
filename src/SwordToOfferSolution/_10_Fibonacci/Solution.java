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
 * 3. 递归改 DP。
 */
public class Solution {
    // 递归
    public static int fib1(int n) {
        return n < 2 ? n : fib1(n - 1) + fib1(n - 2);
    }

    // 备忘录递归
    public static int fib2(int n) {
        if (n < 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // DP!!!
    // 由于之前备忘录递归使用了大小为 N 的数组，所以空间复杂度为 O(N)
    // 但是发现，第 i 项只和第 i-1 项以及第 i-2 项有关，
    // 所以这里只是用三个变量，将空间复杂度降到 O(1)
    public static int fib3(int n) {
        if (n < 2) {
            return n;
        }

        int pre2 = 0;
        int pre1 = 1;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = pre2 + pre1;
            pre2 = pre1;
            pre1 = sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(fib3(1));
    }
}