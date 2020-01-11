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
 */
public class Solution {
    private boolean find(int target, int[][] array) {
        if (array.length == 0 || array[0].length == 0) {
            return false;
        }

        int rows = array.length - 1;
        int columns = array[0].length - 1;
        int row = 0;
        int column = columns;

        while (row <= rows && column >= 0) {
            if (array[row][column] < target) {
                row++;
            } else if (array[row][column] > target) {
                column--;
            } else {
                return true;
            }
        }
        return false;
    }
}
