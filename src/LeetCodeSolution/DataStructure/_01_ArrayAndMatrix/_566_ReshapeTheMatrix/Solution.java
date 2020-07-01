package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._566_ReshapeTheMatrix;

import java.util.Arrays;

/*
 * 重塑矩阵
 *
 * 题目描述：
 * 在 MATLAB 中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
 * 给出一个由二维数组表示的矩阵，以及两个正整数 r 和 c，分别表示想要的重构的矩阵的行数和列数。
 * 重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
 * 如果具有给定参数的 reshape 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
 *
 * 思路：
 * 1. 只需要注意从二维到一维的转换关系即可。
 */
public class Solution {

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums == null || nums.length == 0) {
            return new int[0][];
        }

        int rows = nums.length;
        int cols = nums[0].length;
        if (rows * cols < r * c) {
            return nums;
        }

        int index = 0;
        int[][] res = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                // 核心
                res[i][j] = nums[index / cols][index % cols];
                index++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] nums = {{1, 2}, {3, 4}};

        System.out.println(Arrays.deepToString(solution.matrixReshape(nums, 1, 4)));
        System.out.println(Arrays.deepToString(solution.matrixReshape(nums, 2, 4)));
    }
}
