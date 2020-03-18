package LeetCodeSolution.AlgorithmThought._06_Search._130_SurroundedRegions;

import java.util.Arrays;

/*
 * 被围绕的区域
 *
 * 题目描述：
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 *
 * 思路：
 * 1. 该题与普通的 DFS 区别在于：含有边界上的信息；
 * 2. 首先处理边缘的 'O'，对边缘的 'O' 做 DFS，将遇到的 'O' 变成其它字符；
 * 3. 边缘的 'O' 处理完之后，就可以正常处理内部的 'O' 了；
 * 4. 也是在矩形内部遇到 'O' 之后，做 DFS 的处理。
 */
public class Solution {

    public static void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int rows = board.length;
        int cols = board[0].length;

        // 处理左右边界
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') dfs(board, i, 0);
            if (board[i][cols - 1] == 'O') dfs(board, i, cols - 1);
        }

        // 处理上下边界
        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 'O') dfs(board, 0, j);
            if (board[rows - 1][j] == 'O') dfs(board, rows - 1, j);
        }

        // 遍历整个矩阵
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果遇到了 O，则就需要将 O 变为 X 了
                if (board[i][j] == 'O') board[i][j] = 'X';
                // 如果遇到了 A，则说明之前被边缘的 O 感染过，我们再将被感染过的还原成 O
                if (board[i][j] == 'A') board[i][j] = 'O';
            }
        }
    }

    private static void dfs(char[][] board, int i, int j) {
        // 注意这里的边界，i 和 j 不能到达上下左右四个边界
        if (i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1 || board[i][j] != 'O') {
            return;
        }

        board[i][j] = 'A';
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }

    public static void main(String[] args) {
        char[][] ch = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        solve(ch);
        System.out.println(Arrays.deepToString(ch));
    }
}