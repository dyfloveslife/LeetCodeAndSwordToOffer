package SwordToOfferSolution._12_StringPathInMatrix;

/*
 * 矩阵中的路径
 *
 * 题目描述：
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。
 * 如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 * 例如在下面的 3×4 的矩阵中包含一条字符串 “bfce” 的路径（路径中的字母用下划线标出）。
 * 但矩阵中不包含字符串 “abfb” 的路径，因为字符串的第一个字符 b 占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 *
 * A B T G
 * C F C S
 * J D E H
 *
 * 思路：
 * 1. DFS + 回溯；
 *    剪枝：遇到这条路不可能和目标字符串匹配成功的时候，则立即返回；
 * 2. 按照当前节点的下、上、右、左的顺序开始遍历：
 *    2.1) 如果元素已经访问过了，则返回 false；
 *    2.2) 如果元素越界，则返回 false；
 *    2.3) 如果字符不匹配，则返回 false；
 *    2.4) 如果匹配成功，则返回 true。
 */
class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0
                || word == null || word.length() == 0) {
            return false;
        }

        int m = board.length, n = board[0].length;
        char[] chars = word.toCharArray();
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, chars, visited, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    // index 表示当前目标字符在 word 中的索引
    private static boolean dfs(char[][] board, char[] chars, boolean[][] visited, int i, int j, int index) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length
                || board[i][j] != chars[index] || visited[i][j]) {
            return false;
        }

        // 如果当前遍历的字符已经到达了 chars 的末尾，则说明结束了
        if (index == chars.length - 1) {
            return true;
        }

        // 标记成已经访问过
        visited[i][j] = true;

        boolean res = dfs(board, chars, visited, i + 1, j, index + 1)
                || dfs(board, chars, visited, i - 1, j, index + 1)
                || dfs(board, chars, visited, i, j + 1, index + 1)
                || dfs(board, chars, visited, i, j - 1, index + 1);
        // 回溯
        visited[i][j] = false;
        return res;
    }

    public static void main(String[] args) {
        String word1 = "abce";
        String word2 = "abccee";
        String word3 = "fcces";
        String word4 = "fccse";
        char[][] board = {
                {'a', 'b', 'c', 'e'},
                {'s', 'f', 'c', 's'},
                {'a', 'd', 'e', 'e'}
        };

        Solution solution = new Solution();

        System.out.println(solution.exist(board, word1));
        System.out.println(solution.exist(board, word2));
        System.out.println(solution.exist(board, word3));
        System.out.println(solution.exist(board, word4));
    }
}