package LeetCodeSolution.AlgorithmThought._06_Search._542_01Matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 0/1 矩阵
 *
 * 题目描述：
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 * 两个相邻元素间的距离为 1 。
 *
 * 思路：
 * 1. 使用 BFS。
 */
public class Solution {

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0][];
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] res = new int[m][n];

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] data = queue.poll();
            int x = data[0];
            int y = data[1];
            for (int[] dir : directions) {
                int nextX = dir[0] + x;
                int nextY = dir[1] + y;
                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || visited[nextX][nextY]) {
                    continue;
                }
                res[nextX][nextY] = res[x][y] + 1;
                queue.offer(new int[]{nextX, nextY});
                visited[nextX][nextY] = true;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        System.out.println(Arrays.deepToString(solution.updateMatrix(matrix)));
    }
}
