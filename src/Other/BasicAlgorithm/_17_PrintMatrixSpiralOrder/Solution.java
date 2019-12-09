package Other.BasicAlgorithm._17_PrintMatrixSpiralOrder;

/* 顺时针打印矩阵
 * https://github.com/dyfloveslife/LeetCodeAndSwordToOffer/blob/master/src/SwordToOfferSolution/_29_PrintMatrix/Solution.java
 * 1. 首先确定左上角和右下角的范围，然后打印最外层框；
 * 2. 然后向内移动左上角和右下角的坐标，继续打印内层的框，直至打印结束。
 */
public class Solution {
    public static void printMatrixSpiralOrder(int[][] matrix) {
        // tR 左上角的行
        // tC 左上角的列
        // dR 右下角的行
        // dC 右下角的列
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;

        while (tR <= dR && tC <= dC) {
            printEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    private static void printEdge(int[][] matrix, int tR, int tC, int dR, int dC) {
        // 只有一行的情况
        if (tR == dR) {
            for (int i = tC; i <= dC; i++) {
                System.out.print(matrix[tR][i] + " ");
            }
            // 只有一列的情况
        } else if (tC == dC) {
            for (int i = tR; i <= dR; i++) {
                System.out.print(matrix[tR][i] + " ");
            }
            // 正常情况
            // https://i.loli.net/2019/12/09/sUEZ9dXxYu6t2Dp.png
        } else {
            int curR = tR;
            int curC = tC;
            // 从左到右打印
            while (curC != dC) {
                System.out.print(matrix[tR][curC]);
                curC++;
            }
            // 从上到下打印
            while (curR != dR) {
                System.out.print(matrix[curR][dC]);
                curR++;
            }
            // 从右到左打印
            while (curC != tC) {
                System.out.print(matrix[dR][curC]);
                curC--;
            }
            // 从下到上打印
            while (curR != tR) {
                System.out.print(matrix[curR][tC]);
                curR--;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printMatrixSpiralOrder(matrix);
    }
}
