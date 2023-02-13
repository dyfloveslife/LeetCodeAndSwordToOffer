package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._73_SetMatrixZeroes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * 矩阵置零
 *
 * 题目描述：
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。
 * 请使用原地算法。
 *
 * 思路一：使用额外空间
 * 1. 先遍历一遍矩阵，将元素为 0 的坐标存入 Set；
 * 2. 然后再遍历一遍矩阵，当遍历到 i 或 j 时，如果 Set 中存在对应的值，则将当前坐标置为 0 即可。
 * 3. 时间复杂度 O(mn)，空间复杂度 O(m + n)。
 *
 * 思路二：使用两个标记变量
 * 1. 使用第一行和第一列来代替思路一中的两个数组，但是有可能会改变第一行和第一列的值，导致无法记录它们原本是否包含 0；
 * 2. 因此需要使用两个额外的变量来标识第一行和第一列是否包含 0；
 * 3. 首先遍历第一行和第一列，以此处理该两个变量，如果存在 0，则相应的变量置为 true；
 * 4. 然后用其它行和列去处理第一行和第一列；
 * 5. 用第一行和第一列去处理其它行和列；
 * 6. 最后用之前的两个变量去更新第一行和第一列即可；
 * 7. 时间复杂度 O(mn)，空间复杂度 O(1)。
 *
 * 思路三：使用一个标记
 * 1. 使用第一列来标记原本是否存在 0；
 * 2. 为了防止每一列的第一个元素被提前更新，需要从最后一行开始处理元素；
 * 3. 时间复杂度 O(mn)，空间复杂度 O(1)。
 */
public class Solution {
    // 思路一
    public void setZeroes1(int[][] matrix) {
        Set<Integer> rowZero = new HashSet<>();
        Set<Integer> colZero = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rowZero.add(i);
                    colZero.add(j);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (rowZero.contains(i) || colZero.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // 思路一
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // 思路二
    public void setZeroes3(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean rowFlag = false, colFlag = false;

        // 遍历第一列，如果存在 0，则将标记位置为 true
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                colFlag = true;
                break;
            }
        }
        // 遍历第一行，如果存在 0，则将标记位置为 true
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                rowFlag = true;
                break;
            }
        }
        // 从第二行、第二列开始遍历，如果遇到某个元素为 0，则将第一行、第一列的相应位置元素置为 0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        // 使用第一行、第一列的值去更新其他行、列的值
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 最后再使用之前的标记位，去处理第一行和第一列的值
        if (colFlag) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (rowFlag) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    // 思路三
    public void setZeroes4(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean colFlag = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                colFlag = true;
            }
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (colFlag) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix1 = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        solution.setZeroes1(matrix1);
        System.out.println(Arrays.deepToString(matrix1));
        // [1, 0, 1]
        // [0, 0, 0]
        // [1, 0, 1]

        int[][] matrix2 = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        solution.setZeroes1(matrix2);
        System.out.println(Arrays.deepToString(matrix2));
        // [0, 0, 0, 0]
        // [0, 4, 5, 0]
        // [0, 3, 1, 0]
    }
}
