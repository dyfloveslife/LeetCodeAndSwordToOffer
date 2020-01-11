package Other.BasicAlgorithm._18_RotateMatrix;

/*
 * 将正方形矩阵顺时针旋转 90°
 * 不申请额外空间，即找到哪四个点是一组，然后进行交换。
 */
public class Solution {
    public static void rotate(int[][] matrix) {
        // tR 左上角的行
        // tC 左上角的列
        // dR 右下角的行
        // dC 右下角的列
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;

        while (tR < dR) {
            rotateEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    private static void rotateEdge(int[][] matrix, int tR, int tC, int dR, int dC) {
        // 元素 1 对应四个点，分别交换
        // 元素 2 对应四个点，分别交换
        // 元素 3 对应四个点，分别交换
        int times = dC - tC;
        int temp = 0;
        for (int i = 0; i != times; i++) {
            // 逆时针交换
            // temp =  (0, 0)
            // (0, 0) = (3, 0)
            // (3, 0) = (3, 3)
            // (3, 3) = (0, 3)
            // (0, 0) = temp
            temp = matrix[tR][tC + i];
            matrix[tR][tC + i] = matrix[dR - i][tC];
            matrix[dR - i][tC] = matrix[dR][dC - i];
            matrix[dR][dC - i] = matrix[tR + i][dC];
            matrix[tR + i][dC] = temp;
        }
    }

    public static void printCotateEdge(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printCotateEdge(matrix);
        rotate(matrix);
        System.out.println("-----------");
        printCotateEdge(matrix);
    }
}
