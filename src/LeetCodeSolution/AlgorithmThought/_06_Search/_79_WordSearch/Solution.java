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
 * 2. 但注意边界条件、最终的目标设置以及约束条件；
 * 3. 需要注意的是，其实题目并没有要求必须从 (0, 0) 开始找到目标单词，而是没有设定起始位置；
 * 4. 如果必须从 (0, 0) 开始找目标单词，那么需要在 exist 函数的 for 中直接返回 dfs()，而不是再做判断。
 */
public class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0
                || word == null || word.length() == 0) {
            return false;
        }

        int m = board.length, n = board[0].length;
        char[] ch = word.toCharArray();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (dfs(board, row, col, ch, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, int row, int col, char[] ch, int index) {
        int m = board.length, n = board[0].length;
        // 边界检查
        if (row < 0 || row >= m || col < 0 || col >= n || board[row][col] != ch[index]) {
            return false;
        }

        // 最终情况，如果 index 已经来到的字符串 word 的末尾，则说明遍历结束了
        if (index == ch.length - 1) {
            return true;
        }

        char temp = board[row][col];
        board[row][col] = '#';
        boolean res = dfs(board, row + 1, col, ch, index + 1)
                || dfs(board, row - 1, col, ch, index + 1)
                || dfs(board, row, col + 1, ch, index + 1)
                || dfs(board, row, col - 1, ch, index + 1);
        // 由于涉及到回溯操作，因此需要再次将 [i][j] 位置设置为之前的字符
        // 再从之前的字符开始找另一条路径
        board[row][col] = temp;
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

        Solution solution = new Solution();
        System.out.println(solution.exist(board, word1)); // true
        System.out.println(solution.exist(board, word2)); // true
        System.out.println(solution.exist(board, word3)); // false
    }
}
