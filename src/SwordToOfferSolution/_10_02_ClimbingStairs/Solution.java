package SwordToOfferSolution._10_02_ClimbingStairs;

/*
 * 爬楼梯
 *
 * 题目描述：
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。
 * 你有多少种不同的方法可以爬到楼顶呢？
 *
 * 思路：
 * 与斐波那契数列问题相似，只不过初始值不同。
 */
public class Solution {
    // 递归
    public int climbStairs1(int n) {
        if (n < 2) {
            return 1;
        }
        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }

    // DP 数组
    public int climbStairs2(int n) {
        if (n < 2) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // DP
    public int climbStairs3(int n) {
        if (n < 2) {
            return 1;
        }

        int res = 0;
        int prepre = 1;
        int pre = 1;
        for (int i = 2; i <= n; i++) {
            res = prepre + pre;
            prepre = pre;
            pre = res;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.climbStairs1(7));
        System.out.println(solution.climbStairs2(7));
        System.out.println(solution.climbStairs3(7));
    }
}
