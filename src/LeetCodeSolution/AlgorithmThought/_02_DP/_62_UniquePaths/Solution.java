package LeetCodeSolution.AlgorithmThought._02_DP._62_UniquePaths;

import java.util.Arrays;

/*
 * 不同路径
 *
 * 题目描述：
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start”）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径?
 *
 * 思路：
 * 1. dp[i][j] 表示走到 (i, j) 点的时候，所能够到达的所有不同路径；
 * 2. dp[i][j] = dp[i - 1][j] + dp[i][j - 1]；
 * 3. 对于如何优化到一维空间，由于在二维当中，需要依赖上面一行的值和左边的值；
 * 4. 因此，一次只记一行，当换行的时候数组里面存放的是第 i-1 行的数据。
 */
public class Solution {

    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }

        int[][] dp = new int[m][n];

        // dp 中的第一列只有 1 条路径
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        // dp 中的第一行只有 1 条路径
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    // 优化到一维
    public int uniquePaths2(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        // 这里的 i 用于来到不同的行
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.uniquePaths(3, 2));
        System.out.println(solution.uniquePaths(7, 3));
        System.out.println("==");

        System.out.println(solution.uniquePaths2(3, 2));
        System.out.println(solution.uniquePaths2(7, 3));
    }
}
