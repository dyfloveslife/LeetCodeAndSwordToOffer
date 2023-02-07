package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._48_RotateImage;

import java.util.Arrays;

/*
 * 旋转图像
 *
 * 题目描述：
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 *
 * 思路：
 * 1. 首先找到矩阵的左上角和右下角，外圈交换完后，通过这两个坐标可以进入到内圈，然后再进行交换；
 * 2. 在交换时，注意坐标之间的转换。
 */
public class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        // 拿到左上角、右下角的点
        int leftTopX = 0, leftTopY = 0;
        int rightBottomX = matrix.length - 1, rightBottomY = matrix[0].length - 1;

        while (leftTopX < rightBottomX) {
            process(matrix, leftTopX++, leftTopY++, rightBottomX--, rightBottomY--);
        }
    }

    private void process(int[][] matrix, int leftTopX, int leftTopY, int rightBottomX, int rightBottomY) {
        int times = rightBottomY - leftTopY;
        for (int i = 0; i < times; i++) {
            int temp = matrix[leftTopX][leftTopY + i];
            matrix[leftTopX][leftTopY + i] = matrix[rightBottomX - i][leftTopY];
            matrix[rightBottomX - i][leftTopY] = matrix[rightBottomX][rightBottomY - i];
            matrix[rightBottomX][rightBottomY - i] = matrix[leftTopX + i][rightBottomY];
            matrix[leftTopX + i][rightBottomY] = temp;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] matrix2 = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };

        solution.rotate(matrix1);
        Arrays.stream(matrix1).map(Arrays::toString).forEach(System.out::println);
        // 7, 4, 1
        // 8, 5, 2
        // 9, 6, 3

        System.out.println();
        solution.rotate(matrix2);
        Arrays.stream(matrix2).map(Arrays::toString).forEach(System.out::println);
        // 15, 13,  2,  5
        // 14,  3,  4,  1
        // 12,  6,  8,  9
        // 16,  7, 10, 11
    }
}
