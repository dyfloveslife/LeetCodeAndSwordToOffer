package SwordToOfferSolution._04_FindInPartiallySortedMatrix;

/*
 * 二维数组中的查找
 *
 * 题目描述：
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 思路：
 * 1. 比较矩阵的右上角的数与 target 的大小，如果 target 比这个矩阵右上角的数大，
 *    由于矩阵的右上角元素 A 是 A 所在行的最大的值，所以 target 肯定不在 A 所在的行了，
 *    所以这时候就应该就在除去第一行的剩下的行中去找这个 target;
 * 2. 如果 target 比矩阵右上角的数 A 小，那么由于 A 所在的列中 A 是最小的，那么 target 就在除去最右边的列的其它的列；
 * 3. 如果相等，返回 true;
 * 4. 或者从左下角开始找，如果左下角的元素大于 target，则说明左下角所在的整行都是大于 target 的，直接 row-- 来到上边的一行;
 *    如果左下角的元素小于 target，则说明左下角所在的整列都是小于 target 的，直接 col++ 来到右边的一列;
 *    如果左下角的元素等于 target，则直接返回 true。
 */
public class Solution {
    private boolean find(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        // start from (row, col)
        int row = matrix.length - 1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        Solution solution = new Solution();
        System.out.println(solution.find(matrix, 5));
        System.out.println(solution.find(matrix, 8));
        System.out.println(solution.find(matrix, 20));
    }
}