package Other.BasicAlgorithm._43_MinPathInMatrix;

/*
 * 矩阵中的最小路径和
 *
 * 题目描述：
 * 给定一个都是正数的二维数组，要求从左上角走到右下角，每一步只能向右或者向下走。
 * 沿途经过的数字要累加起来，求最小的路径和。
 *
 * 思路：
 * 1. 如果采用暴力递归的方式，则直接递归就好，但要注意结束的标志；
 * 2. 如果采用 DP，则可以将之前重复计算的值保存下来，等到再次遇到相同值的时候，直接查就可以了；
 * 3. 什么样尝试版本的递归可以改成 DP？
 *    3.1) 即当递归展开的过程中存在重复计算的状态，而且该重复状态是与路径无关的，此时可以改成 DP；
 *    3.2) 不管是往下到达的当前位置，还是往右到达的当前位置，对于当前位置来说，它走到最右下角的位置最短路径和是固定的值，即无后效性问题；
 *    3.3) 对于 process2() 方法来说，如果 i 和 j 固定了，返回值确定了的话，则属于无后效性问题，可以改成 DP。
 * 4. 调用 process2(m, 0, 0) 的时候，(0, 0) 位置是我们最终想要的状态，然后回到递归函数中看看哪些位置的值是不依赖其它位置的；
 * 5. 这里“不依赖其它位置的值”就是 base case，即最右下角的位置，它已经不会再往下划分了；
 * 6. 递归改 DP 的大体流程：
 *    6.1) 写出递归版本；
 *    6.2) 分析可变参数，可变参数是几维的就是一张几维表；
 *    6.3) 分析最终位置是哪一个，在 DP 表中表示出来，然后回到 base case 中将完全不依赖的值设置好；
 *    6.4) 通过一个普遍的位置分析出它需要哪些位置给它提供值，逆着回去就是填表的顺序。
 */
public class Solution {

    /**
     * 暴力递归
     *
     * @param matrix
     * @return
     */
    public static int minPath1(int[][] matrix) {
//        return process1(matrix, matrix.length - 1, matrix[0].length - 1);
        return process2(matrix, 0, 0);
    }

    /**
     * i 表示矩阵的行数，j 表示矩阵的列数
     *
     * @param matrix
     * @param i
     * @param j
     * @return
     */
    private static int process1(int[][] matrix, int i, int j) {
        int res = matrix[i][j];
        if (i == 0 && j == 0) {
            return res;
        }
        if (i == 0 && j != 0) {
            return res + process1(matrix, i, j - 1);
        }
        if (i != 0 && j == 0) {
            return res + process1(matrix, i - 1, j);
        }
        return res + Math.min(process1(matrix, i, j - 1), process1(matrix, i - 1, j));
    }

    /**
     * 从 (i, j) 位置出发，即初始的时候是从 (0, 0) 位置开始
     * 但这种暴力枚举的方法存在重复计算。
     *
     * @param matrix
     * @param i
     * @param j
     * @return
     */
    public static int process2(int[][] matrix, int i, int j) {
        // 如果来到了右下角的位置，则需要返回从右下角到右下角的位置，即右下角的值
        if (i == matrix.length - 1 && j == matrix[0].length - 1) {
            return matrix[i][j];
        }
        // 如果来到最后一行，则只能往右走
        // 问题就等价于：下一个位置的最小路径和加上当前位置值
        if (i == matrix.length - 1) {
            return matrix[i][j] + process2(matrix, i, j + 1);
        }
        // 如果来到最后一列，则只能往下走
        // 问题就等价于：下一个位置的最小路径和加上当前位置的值
        if (j == matrix[0].length - 1) {
            return matrix[i][j] + process2(matrix, i + 1, j);
        }
        // 如果可以往右走或者往下走，则问题等价于：
        // 求往下走或往右走的最小路径和，在这两者中取最小，再加上当前位置的值即可
        int right = process2(matrix, i, j + 1);
        int down = process2(matrix, i + 1, j);
        return matrix[i][j] + Math.min(right, down);
    }

    /**
     * DP 版本
     *
     * @param m
     * @return
     */
    public static int minPath2(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }

        int rows = m.length;
        int columns = m[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = m[0][0];

        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < columns; j++){
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }

        return dp[rows - 1][columns - 1];
    }


    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
//        System.out.println(minPath1(m));
//        System.out.println(minPath1(m));
        System.out.println(minPath2(m));
    }
}
