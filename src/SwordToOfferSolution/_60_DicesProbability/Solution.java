package SwordToOfferSolution._60_DicesProbability;

import java.util.Arrays;

/*
 * n 个骰子的点数
 *
 * 题目描述：
 * 有 n 个骰子扔在地上，所有骰子面朝上的点数之和为 s，输入 n，打印 s 的所有可能值出现的概率。
 *
 * 分析：
 * n 个骰子的点数和最小是 n，最大是 6*n；将 1~n 个骰子的结果排列为一个二维数组 results[n][6 * n]，
 * 发现 results[i][j] 等于 results[i - 1][j - 1] 和其前 5 个数之和。
 *
 * 思路：DP
 * 1. 该题的变量有骰子的个数 n 以及点数之和 s，现在需要求的就是每个点数出现的次数；
 * 2. 记 dp(n,s) 表示当投掷 n 个骰子面朝上的点数之和为 s 时出现的次数，则投掷第 n 个骰子的点数之和只与投掷第 n-1 个骰子有关；
 * 3. 当有 n-1 个骰子的时候，我再想投掷一个的时候，则满足如下关系：
 *    (n-1,s-1)：投掷出了 1；
 *    (n-1,s-2)：投掷出了 2；
 *    (n-1,s-3)：投掷出了 3；
 *    (n-1,s-4)：投掷出了 4；
 *    (n-1,s-5)：投掷出了 5；
 *    (n-1,s-6)：投掷出了 6；
 * 4. 即在 n-1 的基础上，再增加一个骰子所出现的点数和为 s 的结果只有如下 6 种：
 *    dp(n,s)=dp(n-1,s-1)+dp(n-1,s-2)+dp(n-1,s-3)+dp(n-1,s-4)+dp(n-1,s-5)+dp(n-1,s-6)
 * 5. 当投掷一个骰子的时候，满足 dp(1,1)=dp(1,2)=dp(1,3)=dp(1,4)=dp(1,5)=dp(1,6)=1，即每个点数都出现了 1 次；
 * 6. 状态转移方程：dp[n][s]=sum(dp[n-1][s-m])，其中 1<=m<=6 && m < s。
 */
public class Solution {

    public static double[] printProbability(int n) {
        if (n < 1) {
            return null;
        }

        // 构造二维数组
        // dp[n][s] n 个骰子面朝上点数之和为 s 的次数
        int[][] dp = new int[n + 1][6 * n + 1];
        // 初始化
        for (int s = 1; s <= 6; s++) {
            dp[1][s] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= 6 * n; j++) {
                int sum = 0;
                for (int m = 1; m <= j && m <= 6; m++) {
                    sum += dp[i - 1][j - m];
                }
                dp[i][j] = sum;
            }
        }

        // n 个骰子所有点数的可能性
        double total = Math.pow(6, n);
        double[] res = new double[5 * n + 1];
        // 统计结果
        for (int s = n; s <= 6 * n; s++) {
            res[s - n] = ((double)dp[n][s]) / total;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(printProbability(2)));
    }
}
