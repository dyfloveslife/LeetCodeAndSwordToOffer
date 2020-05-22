package SwordToOfferSolution._47_MaxValueOfGifts;

/*
 * 礼物的最大价值
 *
 * 题目描述：
 * 在一个 m×n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向左或者向下移动一格直到到达棋盘的右下角。
 * 给定一个棋盘及其上面的礼物，请计算你最多能拿到多少价值的礼物？
 *
 * 思路：经典 DP 问题
 * 1. 当前节点的最大总价值 = max(上面节点的最大总价值 + 左边节点的最大总价值) + 当前节点最大总价值；
 * 2. 第一行和第一列都要进行初始化。
 */
public class Solution {
    public int getMost1(int[][] values) {
        if (values == null || values.length == 0 || values[0].length == 0) {
            return 0;
        }

        int m = values.length;
        int n = values[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = values[0][0];

        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + values[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + values[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + values[i][j];
            }
        }
        return dp[n - 1][n - 1];
    }

    // 优化后
    public int getMost2(int[][] values) {
        if (values == null || values.length == 0 || values[0].length == 0) {
            return 0;
        }

        int m = values.length;
        int n = values[0].length;

        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[j] = Math.max(dp[j], dp[j - 1]) + values[i - 1][j - 1];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};

        System.out.println(solution.getMost1(grid));
        System.out.println(solution.getMost2(grid));
    }
}
