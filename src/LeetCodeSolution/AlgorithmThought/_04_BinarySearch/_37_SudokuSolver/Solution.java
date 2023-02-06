package LeetCodeSolution.AlgorithmThought._04_BinarySearch._37_SudokuSolver;

import java.util.Arrays;

/*
 * 解数独
 *
 * 题目描述：
 * 编写一个程序，通过填充空格来解决数独问题。
 *
 * 数独的解法需 遵循如下规则：
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 *
 * 思路：
 * 1. 回溯思想，整体思路是首先尝试填充，如果发现重复了，那么回到之前的状态后重新进行新一轮的尝试，直到把整个矩阵填完；
 * 2. 先定义三个 boolean 数组，用于表示原始矩阵中的数字是否已经被使用过了；
 * 3. 对于回溯过程的终止条件，如果行和列都走到头了，则说明处理完成；
 * 4. 在尝试填充之前，需要先检查当前位置是否为数字，如果为数字则填充下一位置；
 * 5. 尝试将数字填充到当前位置，并修改三个 boolean 数组的状态；
 * 6. 若当前位置的数字合法，则处理下一位置，否则回溯。
 */
public class Solution {
    public void solveSudoku1(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return;
        }

        // true 表示某个原始位置的数字已经被使用过了
        boolean[][] rowUsed = new boolean[9][9];
        boolean[][] colUsed = new boolean[9][9];
        boolean[][][] boxUsed = new boolean[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }

                int idx = c - '0' - 1;
                rowUsed[i][idx] = true;
                colUsed[j][idx] = true;
                boxUsed[i / 3][j / 3][idx] = true;
            }
        }

        backtrack(board, rowUsed, colUsed, boxUsed, 0, 0);
    }

    private boolean backtrack(char[][] board, boolean[][] rowUsed, boolean[][] colUsed, boolean[][][] boxUsed, int row, int col) {
        if (col == board[0].length) {
            col = 0;
            row++;
            // 结束条件
            if (row == board.length) {
                return true;
            }
        }

        // 如果当前位置是数字，则填充下一个位置
        if (board[row][col] != '.') {
            return backtrack(board, rowUsed, colUsed, boxUsed, row, col + 1);
        }

        // 尝试填充
        for (int idx = 0; idx < 9; idx++) {
            boolean used = rowUsed[row][idx] || colUsed[col][idx] || boxUsed[row / 3][col / 3][idx];
            if (used) {
                continue;
            }

            // 当前尝试使用一个数字，并且标记为已使用过
            // convert integer number to char number
            board[row][col] = (char) ('1' + idx);
            rowUsed[row][idx] = true;
            colUsed[col][idx] = true;
            boxUsed[row / 3][col / 3][idx] = true;

            if (backtrack(board, rowUsed, colUsed, boxUsed, row, col + 1)) {
                return true;
            }

            // 回溯
            board[row][col] = '.';
            rowUsed[row][idx] = false;
            colUsed[col][idx] = false;
            boxUsed[row / 3][col / 3][idx] = false;
        }

        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        solution.solveSudoku1(board);
        Arrays.stream(board).map(Arrays::toString).forEach(System.out::println);
    }
}
