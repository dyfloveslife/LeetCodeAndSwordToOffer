package LeetCodeSolution.AlgorithmThought._02_DP._509_FibonacciNumber;

/*
 * 斐波那契数
 *
 * 题目描述：
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为斐波那契数列。
 * 该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 给定 N，计算 F(N)。
 *
 * 思路：
 * 1. 递归；
 * 2. 带有备忘录的 DP；
 * 3. DP
 */
public class Solution {

    // 递归
    public int fib1(int n) {
        if (n < 2) {
            return n;
        }

        return fib1(n - 1) + fib1(n - 2);
    }

    // 带有备忘录的 DP
    public int fib2(int n) {
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

    // DP
    public int fib3(int n) {
        if (n < 2) {
            return n;
        }

        int prepre = 0;
        int pre = 1;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = prepre + pre;
            prepre = pre;
            pre = res;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.fib1(4));
        System.out.println(solution.fib2(4));
        System.out.println(solution.fib3(4));
    }
}
