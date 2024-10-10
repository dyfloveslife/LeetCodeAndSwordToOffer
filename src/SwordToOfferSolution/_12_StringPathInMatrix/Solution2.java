package SwordToOfferSolution._12_StringPathInMatrix;

public class Solution2 {

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0
                || word == null || word.isEmpty()) {
            return false;
        }

        int m = board.length;
        int n = board[0].length;
        char[] chars = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, chars, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, char[] chars, int i, int j, int index) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != chars[index]) {
            return false;
        }

        if (index == chars.length - 1) {
            return true;
        }

        board[i][j] = '#';
        boolean ans = dfs(board, chars, i + 1, j, index + 1)
                || dfs(board, chars, i - 1, j, index + 1)
                || dfs(board, chars, i, j + 1, index + 1)
                || dfs(board, chars, i, j - 1, index + 1);
        board[i][j] = chars[index];
        return ans;
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


        Solution2 solution2 = new Solution2();
        System.out.println(solution2.exist(board, word1));
        System.out.println(solution2.exist(board, word2));
        System.out.println(solution2.exist(board, word3));
        System.out.println(solution2.exist(board, word4));
    }
}
