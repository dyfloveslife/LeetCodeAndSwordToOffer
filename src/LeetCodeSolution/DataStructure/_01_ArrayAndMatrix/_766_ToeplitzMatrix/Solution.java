package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._766_ToeplitzMatrix;

/*
 * 托普利茨矩阵
 *
 * 题目描述：
 * 如果一个矩阵的每一方向由左上到右下的对角线上具有相同元素，那么这个矩阵是托普利茨矩阵。
 * 给定一个 M x N 的矩阵，当且仅当它是托普利茨矩阵时返回 True。
 *
 * 思路：
 * 1. 观察矩阵元素的规律，我们发现只需要比较当前行中除了最后一个元素的其它元素是否与下一行的除了第一个元素之外的其它元素是否对应相等即可；
 */
public class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[0].length - 1; j++) {
                if (matrix[i][j] != matrix[i + 1][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix1 = {
                {1, 2, 3, 4},
                {5, 1, 2, 3},
                {9, 5, 1, 2}
        };
        int[][] matrix2 = {{1, 2}, {2, 2}};

        System.out.println(solution.isToeplitzMatrix(matrix1));
        System.out.println(solution.isToeplitzMatrix(matrix2));
    }
}
