package LeetCodeSolution.AlgorithmThought._06_Search._417_PacificAtlanticWaterFlow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 太平洋大西洋水流问题
 *
 * 题目描述：
 * 1. 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
 * 2. 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 * 3. 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 * 4. 示例：
 *           太平洋 ~   ~   ~   ~   ~
 *             ~  1   2   2   3  (5) *
 *             ~  3   2   3  (4) (4) *
 *             ~  2   4  (5)  3   1  *
 *             ~ (6) (7)  1   4   5  *
 *             ~ (5)  1   1   2   4  *
 *                *   *   *   *   * 大西洋
 *
 * 5. 输出：[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
 *
 * 思路：
 * 0. 使用 DFS；
 * 1. 建立两个矩阵 Pacific（太平洋）和 Atlantic（大西洋），
 *    当 Atlantic[i][j] 和 Pacific[i][j] 同时为 true 时，表示当前位置 [i][j] 可以同时到达 Atlantic 和 Pacific；
 * 2. 从四个边界出发，如果能够到达的话，则说明当前位置的水可以流向对应的海洋。
 */
public class Solution {

    public static List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }

        int m = matrix.length, n = matrix[0].length;

        boolean[][] Pacific = new boolean[m][n];
        boolean[][] Atlantic = new boolean[m][n];

        // 注意每个边界都对应不同的海洋，需要分别处理
        for (int i = 0; i < n; i++) {
            // 上边界
            dfs(matrix, 0, i, Pacific, matrix[0][i]);
            // 下边界
            dfs(matrix, m - 1, i, Atlantic, matrix[m - 1][i]);
        }

        for (int j = 0; j < m; j++) {
            // 左边界
            dfs(matrix, j, 0, Pacific, matrix[j][0]);
            // 右边界
            dfs(matrix, j, n - 1, Atlantic, matrix[j][n - 1]);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 当前的位置 [i][j] 可以到达大西洋和太平洋，
                // 将该位置添加到结果集中
                if (Pacific[i][j] && Atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    // pre_h 表示之前的高度
    private static void dfs(int[][] matrix, int i, int j, boolean[][] visited, int pre_h) {
        // 边界检查
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length
                || visited[i][j] || matrix[i][j] < pre_h) {
            return;
        }

        visited[i][j] = true;
        dfs(matrix, i + 1, j, visited, matrix[i][j]);
        dfs(matrix, i - 1, j, visited, matrix[i][j]);
        dfs(matrix, i, j + 1, visited, matrix[i][j]);
        dfs(matrix, i, j - 1, visited, matrix[i][j]);
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };

        System.out.println(pacificAtlantic(arr));
    }
}