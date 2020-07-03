package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._74_SearchA2DMatrix;

/*
 * 搜索二维矩阵
 *
 * 题目描述：
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。
 * 该矩阵具有如下特性：
 *     每行中的整数从左到右按升序排列。
 *     每行的第一个整数大于前一行的最后一个整数。
 *
 * 思路：
 * 1. 这里需要根据二维矩阵的特性来确定某个元素在二维矩阵中的位置；
 * 2. 例如对于二维矩阵中任意一个元素 middle，它在二维矩阵中的位置就是 matrix[middle / cols][middle % cols]；
 * 3. 由于数组是有序的，因此可以利用二分的方式，每次排除掉一半的元素；
 * 4. 由于经典的二分最后返回的是 left，因此，该题最后还需要对 left 位置上的数进行确认，判断 left 位置上的数是不是等于 target。
 */
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        // 相当于一维数组的左右边界
        int left = 0;
        int right = rows * cols - 1;

        while (left < right) {
            int middle = left + ((right - left) >> 1);
            // 如果当前位置上的数比 target 小，则说明没有找到，因此需要将 left 范围往右移动
            if (matrix[middle / cols][middle % cols] == target) {
                return true;
            } else if (matrix[middle / cols][middle % cols] < target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        if (matrix[left / cols][left % cols] != target) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};

        System.out.println(solution.searchMatrix(matrix, 3));
        System.out.println(solution.searchMatrix(matrix, 12));
    }
}
