package Other.BasicAlgorithm._21_FindNumInSortedMatrix;

/*
 * 在排好序的 N*M 的矩阵中找到指定的数
 * 时间复杂度：O(N+M)
 * 空间复杂度：O(1)
 *
 * https://github.com/dyfloveslife/LeetCodeAndSwordToOffer/blob/master/src/SwordToOfferSolution/_04_FindInPartiallySortedMatrix/Solution.java
 */
public class Solution {
    public static boolean isContains(int[][] matrix, int K) {
        int row = 0;
        int col = matrix[0].length - 1;

        while (row < matrix.length && col > -1) {
            if (matrix[row][col] == K) {
                return true;
            } else if (matrix[row][col] > K) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
}
