package LeetCodeSolution.AlgorithmThought._06_Search._695_MaxAreaOfIsland;

/*
 * 岛屿的最大面积
 *
 * 题目描述：
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。
 * 你可以假设二维矩阵的四个边缘都被水包围着。
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 *
 * 思路：
 * 1. 使用 DFS+栈 的方式；
 * 2. 每次从函数 dfs 中返回的结果取最大的值；
 * 3. 每次将已经访问过的置为 0，或者置为非 1 的数字，避免下次重复访问；
 */
public class Solution {
    public static int maxAreaOfIsland(int[][] grid) {

        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(grid, i, j));
                }
            }
        }
        return res;
    }

    public static int dfs(int[][] grid, int i, int j) {
        // 边界检查
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }

        grid[i][j] = 0;

        int count = 1;
        count += dfs(grid, i - 1, j);
        count += dfs(grid, i + 1, j);
        count += dfs(grid, i, j - 1);
        count += dfs(grid, i, j + 1);
        return count;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        System.out.println(maxAreaOfIsland(arr));
    }
}
