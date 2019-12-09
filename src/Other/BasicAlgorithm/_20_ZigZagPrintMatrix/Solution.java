package Other.BasicAlgorithm._20_ZigZagPrintMatrix;

/*
 * 之字形打印矩阵
 * https://i.loli.net/2019/12/09/gr27ViOonkjflEP.png
 * 用 A 和 B 控制点的位置，用一个布尔变量控制从左下到右上，还是右上到左下。
 */
public class Solution {
    public static void printMatrixZigZag(int[][] matrix) {
        int aR = 0;
        int aC = 0;
        int bR = 0;
        int bC = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;

        // 点 A 走到右边，然后向下走，直至走到最后
        while (aR != endR + 1) {
            printLevel(matrix, aR, aC, bR, bC, fromUp);
            // A 的列数来到最后一列，这时 A 才往下走，即行数增加，否则不变
            aR = aC == endC ? aR + 1 : aR;
            aC = aC == endC ? aC : aC + 1;
            bC = bR == endR ? bC + 1 : bC;
            bR = bR == endR ? bR : bR + 1;
            fromUp = !fromUp;
        }
        System.out.println();
    }

    // 具体的打印方式
    private static void printLevel(int[][] matrix, int aR, int aC, int bR, int bC, boolean fromUp) {
        if (fromUp) {
            while (aR != bR + 1) {
                System.out.println(matrix[aR++][aC--] + " ");
            }
        } else {
            while (bR != aR - 1) {
                System.out.println(matrix[bR--][bC++] + " ");
            }
        }
    }
}
