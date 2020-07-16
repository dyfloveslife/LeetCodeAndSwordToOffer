package LeetCodeSolution.AlgorithmThought._06_Search._200_NumberOfIslands;

/*
 * 岛的数量
 *
 * 题目描述：
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
 * 你可以假设网格的四个边均被水包围。
 *
 * 思路：
 * 1. 使用 dfs；
 * 2. 将矩阵看成一个有向图。
 */
public class Solution {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 如果当前位置是岛屿，那么我就通过 dfs 的方式，从当前位置的上下左右四个方向去继续找岛屿
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    public void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        // 不执行 if，则说明我们遇到了岛屿，也就是 1，因此需要将当前的 1 置为 0，用于表示已经访问过了
        grid[i][j] = '0';

        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] grid = {{'1','1','0','0','0'}, {'1','1','0','0','0'}, {'0','0','1','0','0'}, {'0','0','0','1','1'}};

        System.out.println(solution.numIslands(grid));
    }
}
