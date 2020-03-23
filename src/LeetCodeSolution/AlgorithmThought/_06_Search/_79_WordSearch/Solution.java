package LeetCodeSolution.AlgorithmThought._06_Search._79_WordSearch;

/*
 * 单词搜索
 *
 * 题目描述：
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 *
 * 思路：
 * 1. 直接使用回溯法；
 * 2. 但注意边界条件、最终的目标设置以及约束条件。
 */
public class Solution {
    public static boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0
            || word == null || word.length() == 0) {
            return false;
        }

        char[] ch = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, ch, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(char[][] board, int i, int j, char[] ch, int index) {
        // check the boundary
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != ch[index]) {
            return false;
        }

        // 最终情况，如果 index 已经来到的字符串 word 的末尾，则说明遍历结束了
        if (index == ch.length - 1) {
            return true;
        }

        char temp = board[i][j];
        board[i][j] = '#';
        boolean res = dfs(board, i + 1, j, ch, index + 1)
                || dfs(board, i - 1, j, ch, index + 1)
                || dfs(board, i, j + 1, ch, index + 1)
                || dfs(board, i, j - 1, ch, index + 1);
        board[i][j] = temp;
        return res;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'},
        };
        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";
        System.out.println(exist(board, word1)); // true
        System.out.println(exist(board, word2)); // true
        System.out.println(exist(board, word3)); // false
    }
}
