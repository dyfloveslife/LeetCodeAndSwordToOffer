package SwordToOfferSolution._10_01_Fibonacci;

/*
 * 斐波那契数列
 *
 * 题目描述：
 * 写一个函数，输入 n，求斐波那契（Fibonacci）数列的第 n 项。
 *
 * 思路：
 * 1. 递归；
 * 2. 带有备忘录的 DP 数组迭代；
 * 3. DP。
 */
public class Solution {
    // 递归
    public int fib1(int n) {
        return n < 2 ? n : fib1(n - 1) + fib1(n - 2);
    }

    // DP 数组迭代
    public int fib2(int n) {
        if (n < 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        // 注意 i 的取值范围，画图理解
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // DP
    // 由于之前备忘录递归使用了大小为 N 的数组，所以空间复杂度为 O(N)
    // 但是发现，第 i 项只和第 i-1 项以及第 i-2 项有关，
    // 所以这里只是用三个变量，将空间复杂度降到 O(1)
    public int fib3(int n) {
        if (n < 2) {
            return n;
        }

        int res = 0;
        int prepre = 0;
        int pre = 1;

        for (int i = 2; i <= n; i++) {
            res = pre + prepre;
            prepre  = pre;
            pre = res;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.fib1(45));
        System.out.println(solution.fib2(45));
        System.out.println(solution.fib3(45));
    }
}