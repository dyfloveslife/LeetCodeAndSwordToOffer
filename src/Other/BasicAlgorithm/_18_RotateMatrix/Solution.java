package Other.BasicAlgorithm._18_RotateMatrix;

import java.util.Arrays;

/*
 * 旋转矩阵
 *
 * 题目描述：
 * 将正方形矩阵顺时针旋转 90°。
 *
 * 思路：
 * 1. 不申请额外空间，即找到哪四个点是一组，然后进行交换；
 * 2. 交换的时候先将左上角的点保存下来，然后分为如下几步：
 *    将左上角的点指向左下角的点，
 *    将左下角的点指向右下角的点，
 *    将右下角的点指向右上角的点，
 *    将右上角的点指向左上角的点。
 * 3. 交换完 4 个点后，然后 i++，来到下一个位置继续交换下一轮的 4 个点。
 */
public class Solution {
    public void rotate(int[][] matrix) {
        // 左上角的点 (lRow, lCol)
        // 右下角的点 (rRow, rCol)
        int lRow = 0;
        int lCol = 0;
        int rRow = matrix.length - 1;
        int rCol = matrix[0].length - 1;

        while (lRow < rRow) {
            process(matrix, lRow++, lCol++, rRow--, rCol--);
        }
    }

    public void process(int[][] matrix, int lRow, int lCol, int rRow, int rCol) {
        int times = rCol - lCol;
        int temp = 0;
        for (int i = 0; i < times; i++) {
            // i++ 就是来到下一个位置再继续进行 4 个点的交换
            // 逆时针交换
            // temp =  (0, 0)
            // (0, 0) = (3, 0)
            // (3, 0) = (3, 3)
            // (3, 3) = (0, 3)
            // (0, 0) = temp
            temp = matrix[lRow][lCol + i];
            matrix[lRow][lCol + i] = matrix[rRow - i][lCol];
            matrix[rRow - i][lCol] = matrix[rRow][rCol - i];
            matrix[rRow][rCol - i] = matrix[lRow + i][rCol];
            matrix[lRow + i][rCol] = temp;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,   2,  3,  4},
                {5,   6,  7,  8},
                {9,  10, 11, 12},
                {13, 14, 15, 16}
        };

        Solution solution = new Solution();
        solution.rotate(matrix);

        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
