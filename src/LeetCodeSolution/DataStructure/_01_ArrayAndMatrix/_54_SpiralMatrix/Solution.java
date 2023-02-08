package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._54_SpiralMatrix;

import java.util.ArrayList;
import java.util.List;

/*
 * 螺旋矩阵
 *
 * 题目描述：
 * 给你一个 m 行 n 列的矩阵 matrix，请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 其中 1 <= m,n <= 10
 *
 * 思路：模拟
 * 1. 拿到左上角和右下角的两个坐标，外圈遍历完后，再通过坐标移动到内圈；
 * 2. 细节是魔鬼，注意每种情况下的坐标不要搞错了。
 */
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return ans;
        }

        int lRow = 0, lCol = 0;
        int rRow = matrix.length - 1, rCol = matrix[0].length - 1;
        while (lRow <= rRow && lCol <= rCol) {
            // 只有一行，有可能给定的 matrix 只有一行元素
            // 也有可能是在遍历完多层 matrix 后，最后剩下了一行元素
            // 后者给定的 m 和 n 不相等
            if (lRow == rRow) {
                for (int i = lCol; i <= rCol; i++) {
                    ans.add(matrix[rRow][i]);
                }
            // 只有一列
            } else if (lCol == rCol) {
                for (int i = lRow; i <= rRow; i++) {
                    ans.add(matrix[i][lCol]);
                }
            // 多行多列
            } else {
                // 因为在打印的过程中坐标会不断变化，所以需要两个临时变量
                int curRow = lRow, curCol = lCol;
                // 左 -> 右，行不变，列++
                while (curCol != rCol) {
                    ans.add(matrix[lRow][curCol++]);
                }
                // 上 -> 下，列不变，行++
                while (curRow != rRow) {
                    ans.add(matrix[curRow++][rCol]);
                }
                // 右 -> 左，行不变，列--
                while (curCol != lCol) {
                    ans.add(matrix[rRow][curCol--]);
                }
                // 下 -> 上，列不变，行--
                while (curRow != lRow) {
                    ans.add(matrix[curRow--][lCol]);
                }
            }

            lRow++;
            lCol++;
            rRow--;
            rCol--;
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix1 = {
                {1, 2},
                {3, 4}
        };

        int[][] matrix2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] matrix3 = {
                {1,  2,  3,  4},
                {5,  6,  7,  8},
                {9, 10, 11, 12}
        };
        int[][] matrix4 = {
                {3},
                {2}
        };

        System.out.println(solution.spiralOrder(matrix1)); // [1, 2, 4, 3]
        System.out.println(solution.spiralOrder(matrix2)); // [1, 2, 3, 6, 9, 8, 7, 4, 5]
        System.out.println(solution.spiralOrder(matrix3)); // [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
        System.out.println(solution.spiralOrder(matrix4)); // [3, 2]
    }
}
