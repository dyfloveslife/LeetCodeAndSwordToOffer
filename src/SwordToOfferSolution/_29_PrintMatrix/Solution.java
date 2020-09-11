package SwordToOfferSolution._29_PrintMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 顺时针打印矩阵
 *
 * 题目描述：
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 * 思路：
 * 写一个函数专用于打印矩阵的一圈，然后在主方法中，等到打印完一圈后，重新设置左上角和右下角的位置，再进行打印即可。
 */
public class Solution {
    public List<Integer> spiralOrder1(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }

        int leftRow = 0;
        int leftCol = 0;
        int rightRow = matrix.length - 1;
        int rightCol = matrix[0].length - 1;

        while (leftRow <= rightRow && leftCol <= rightCol) {
            // 只有一行的情况，我需要将这一行的每个元素添加到 res 中
            if (leftRow == rightRow) {
                // 注意：这里最好使用 for 循环，因为 leftCol 需要来到 rightCol 的位置
                while (leftCol != rightCol + 1) {
                    res.add(matrix[leftRow][leftCol]);
                    leftCol++;
                }
                // 只有一列的情况，那我就去将这一列的元素都添加到 res 中
            } else if (leftCol == rightCol) {
                // 注意：这里最好使用 for 循环，因为 leftRow 需要来到 rightRow 的位置
                while (leftRow != rightRow + 1) {
                    res.add(matrix[leftRow][leftCol]);
                    leftRow++;
                }
                // 多行多列的情况
            } else {
                int curRow = leftRow;
                int curCol = leftCol;
                // 从左到右，变化的是列，不变的是行
                while (curCol != rightCol) {
                    res.add(matrix[leftRow][curCol]);
                    curCol++;
                }
                // 从上到下，变化的是行，不变的是列
                while (curRow != rightRow) {
                    res.add(matrix[curRow][rightCol]);
                    curRow++;
                }
                // 从右到左，变化的是列，不变的是行
                while (curCol != leftCol) {
                    res.add(matrix[rightRow][curCol]);
                    curCol--;
                }
                // 从下到上，变化的是行，不变的是列
                while(curRow != leftRow) {
                    res.add(matrix[curRow][leftCol]);
                    curRow--;
                }
            }
            leftRow++;
            leftCol++;
            rightRow--;
            rightCol--;
        }
        return res;
    }

    public int[] spiralOrder2(int[][] matrix) {
        int left_row = 0;
        int left_col = 0;
        int right_row = matrix.length - 1;
        int right_col = matrix[0].length - 1;

        int[] res = new int[(right_col + 1) * (right_row + 1)];
        int index = 0;

        while (left_row <= right_row && left_col <= right_col) {
            // 只有一行
            if (left_row == right_row) {
                for (int i = left_col; i <= right_col; i++) {
                    res[index++] = matrix[left_row][i];
                }
                // 只有一列
            } else if (left_col == right_col) {
                for (int i = left_row; i <= right_row; i++) {
                    res[index++] = matrix[i][left_col];
                }
                // 多行多列情况（注意以哪个为基准）
            } else {
                int cur_row = left_row;
                int cur_col = left_col;
                // 从左到右
                while (cur_col != right_col) {
                    res[index++] = matrix[left_row][cur_col];
                    cur_col++;
                }
                // 从上到下
                while (cur_row != right_row) {
                    res[index++] = matrix[cur_row][right_col];
                    cur_row++;
                }
                // 从右到左
                while (cur_col != left_col) {
                    res[index++] = matrix[right_row][cur_col];
                    cur_col--;
                }
                // 从下到上
                while (cur_row != left_row) {
                    res[index++] = matrix[cur_row][left_col];
                    cur_row--;
                }
            }
            left_row++;
            left_col++;
            right_row--;
            right_col--;
        }
        return res;
    }

    // 之前的方法
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null || row <= 0 || col <= 0) {
            return list;
        }

        // 定义左上和右下两个点
        int left = 0, top = 0, right = col - 1, bottom = row - 1;
        while (left <= right && top <= bottom) {
            // left to right
            for (int i = left; i <= right; i++) {
                // or matrix[left][i]
                list.add(matrix[top][i]);
            }
            // top to bottom
            for (int i = top + 1; i <= bottom; i++) {
                list.add(matrix[i][right]);
            }
            // 以下两种情况需要考虑到单行或者单列的情况
            // right to left
            if (top != bottom) {
                for (int i = right - 1; i >= left; i--) {
                    list.add(matrix[bottom][i]);
                }
            }
            // bottom to top
            // i 只大于 top，是因为之前访问过了
            if (left != top) {
                for (int i = bottom - 1; i > top; i--) {
                    // or matrix[i][top]
                    list.add(matrix[i][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] arr1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        int[][] arr2 = {{1,2,3}, {4,5,6}, {7,8,9}};

        System.out.println(solution.spiralOrder1(arr1));
        System.out.println(solution.spiralOrder1(arr2));
        System.out.println("-------------------------");

        System.out.println(Arrays.toString(solution.spiralOrder2(arr1)));
        System.out.println(Arrays.toString(solution.spiralOrder2(arr2)));
    }
}
