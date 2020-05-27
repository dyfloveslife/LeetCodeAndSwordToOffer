package LeetCodeSolution.AlgorithmThought._02_DP._64_MinimumPathSum;

/*
 * 最小路径和
 *
 * 题目描述：
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 思路：
 * 1. dp[i][j] 表示走到 (i, j) 的最小路径和；
 * 2. 需要注意的是：这里不需要开一个 dp 数组，浪费额外的辅助空间，而是直接遍历 grid[i][j] 修改即可；
 * 3. grid[i][j] = min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j]。
 */
public class Solution {
    // 占用了额外的辅助空间
    public int minPathSum1(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        // 开始填写 dp 数组
        dp[0][0] = grid[0][0];

        // 先初始化左/上边界
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j - 1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    // 在原数组中修改
    public int minPathSum2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                } else {
                    grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }
            }
        }
        return grid[m - 1][n - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = {{1,3,1}, {1,5,1}, {4,2,1}};

        System.out.println(solution.minPathSum1(grid));
        System.out.println(solution.minPathSum2(grid));
    }
}
