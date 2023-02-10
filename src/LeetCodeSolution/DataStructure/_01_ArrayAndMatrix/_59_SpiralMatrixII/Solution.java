package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._59_SpiralMatrixII;

import java.util.Arrays;

/*
 * 螺旋矩阵 II
 *
 * 题目描述：
 * 给你一个正整数 n，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix。
 *
 * 思路：模拟
 * 1. 定义左、右、上、下、边界分别为 l、r、t、b，初始填入的第一个数 num 为 1；
 * 2. 按照从左到右、从上到下、从右到左、从下到上的顺序填入 num，每次填入后 num++，同时更新边界，即向内缩 1 个单位的距离
 */
public class Solution {

    public int[][] generateMatrix(int n) {
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int[][] matrix = new int[n][n];
        int num = 1;
        while (num <= n * n) {
            // 左 -> 右
            for (int i = l; i <= r; i++) {
                matrix[t][i] = num++;
            }
            t++;
            // 上 -> 下
            for (int i = t; i <= b; i++) {
                matrix[i][r] = num++;
            }
            r--;
            // 右 -> 左
            for (int i = r; i >= l; i--) {
                matrix[b][i] = num++;
            }
            b--;
            // 下 -> 上
            for (int i = b; i >= t; i--) {
                matrix[i][l] = num++;
            }
            l++;
        }

        return matrix;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(Arrays.deepToString(solution.generateMatrix(3))); // [[1, 2, 3], [8, 9, 4], [7, 6, 5]]
        System.out.println(Arrays.deepToString(solution.generateMatrix(1))); // [[1]]
    }
}
