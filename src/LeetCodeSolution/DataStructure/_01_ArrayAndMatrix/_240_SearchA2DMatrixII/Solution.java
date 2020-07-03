package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._240_SearchA2DMatrixII;

/*
 * 搜索二维矩阵 Ⅱ
 *
 * 题目描述：
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。
 * 该矩阵具有以下特性：
 *     每行的元素从左到右升序排列。
 *     每列的元素从上到下升序排列。
 *
 * 思路：
 * 1. 剑指 Offer 的原题，注意观察矩阵元素的特点；
 * 2. 可以从左下角或者右上角的元素开始进行判断，每次如果不符合条件，则去掉相应的行或列。
 */
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = 0;
        int cols = matrix[0].length - 1;

        while (rows < matrix.length && cols > -1) {
            int cur = matrix[rows][cols];
            if (cur == target) {
                return true;
            } else if (cur > target) {
                cols--;
            } else {
                rows++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        System.out.println(solution.searchMatrix(matrix, 5));
        System.out.println(solution.searchMatrix(matrix, 20));
    }
}
