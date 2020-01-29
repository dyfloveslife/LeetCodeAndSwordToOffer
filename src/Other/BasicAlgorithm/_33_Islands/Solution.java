package Other.BasicAlgorithm._33_Islands;

/*
 * 岛问题
 *
 * 题目描述：
 * 一个矩阵中只有 0 和 1 两种值，每个位置都可以和自己的上下左右四个位置相连，
 * 假如有多个 1 连在一起，则称该部分为一个岛，求一个矩阵中有多少个岛？
 *
 * 0 0 1 0 1 0
 * 1 1 1 0 1 0
 * 1 0 0 1 0 0
 * 0 0 0 0 0 0
 *
 * 该矩阵中有 3 个岛
 *
 * 思路 1：
 * 1. 如果是单 CPU、单机器的情况下，即矩阵数量不多的情况；
 * 2. 可以遍历矩阵中的每个数，在遍历的时候调用一个“感染”函数，如果当前的数是 1 ，则将它与它相邻的数都变为 2，然后岛的数量加一；
 * 3. 然后继续遍历判断下一个数，遇到 2 则往后遍历，直至结束。
 *
 * 思路 2：
 * 1. 如果是多 CPU、多机器情况下，即矩阵数量很多的情况；
 * 2. 如果将矩阵拆分成多块，每块交给不同的机器去处理，需要考虑到如何将每块进行结合起来；
 * 3. 其实，可以统计每块岛的数量，然后只需要关心块与块之间的边界信息即可；
 * 4. 边界之间的数值如果都是 1 的话，则岛的数量应该减一，但是应做到不能重复减；
 * 5. 这里就可以使用并查集的方法。
 */
public class Solution {

    // 思路 1
    public static int countIsLands(int[][] matrix) {
        if (matrix == null || matrix[0] == null) {
            return 0;
        }

        int rows = matrix.length;
        int columns = matrix[0].length;
        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == 1) {
                    res++;
                    infect(matrix, i, j, rows, columns);
                }
            }
        }
        return res;
    }

    public static void infect(int[][] matrix, int i, int j, int rows, int columns) {
        if (i < 0 || i >= rows || j < 0 || j >= columns || matrix[i][j] != 1) {
            return;
        }

        // 将当前值该为 2，然后继续感染上下左右
        matrix[i][j] = 2;
        infect(matrix, i + 1, j, rows, columns);
        infect(matrix, i - 1, j, rows, columns);
        infect(matrix, i, j + 1, rows, columns);
        infect(matrix, i, j - 1, rows, columns);
    }
}
