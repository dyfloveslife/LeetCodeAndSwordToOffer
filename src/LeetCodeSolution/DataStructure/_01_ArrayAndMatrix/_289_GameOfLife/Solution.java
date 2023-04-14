package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._289_GameOfLife;

import java.util.Arrays;

/*
 * 生命游戏
 *
 * 题目描述：
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。
 * 每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。
 * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * - 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * - 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * - 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * - 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 *
 * "下一个状态"是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 * 给你 m x n 网格面板 board 的当前状态，返回"下一个状态"。
 *
 * 思路：
 * 1. 用 0 代表死细胞，1 代表活细胞，用 [下一状态, 当前状态] 的格式可表示四种状态：
 *    状态 0: 00，当前是死细胞，下一状态还是死细胞；
 *    状态 1：01，当前是活细胞，下一状态变为死细胞；
 *    状态 2：10，当前是死细胞，下一状态变为活细胞；
 *    状态 3：11，当前是活细胞，下一状态还是活细胞；
 * 2. 遍历矩阵，我们把状态 2 下的元素置为 2，状态 3 下的元素置为 3；
 * 3. 然后更新其状态即可。
 */
public class Solution {
    // 定义当前格子移动的方向
    int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    int[][] board;
    int m, n;

    public void gameOfLife(int[][] board) {
        if (board == null
                || board.length == 0
                || board[0] == null
                || board[0].length == 0) {
            return;
        }

        this.board = board;
        this.m = board.length;
        this.n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 获取 (i, j) 周围活细胞数量
                int cnt = countAlive(i, j);
                // 活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活
                if (board[i][j] == 1 && (cnt == 2 || cnt == 3)) {
                    board[i][j] = 3;
                }
                // 死细胞周围正好有三个活细胞，则该位置死细胞复活
                if (board[i][j] == 0 && cnt == 3) {
                    board[i][j] = 2;
                }
            }
        }

        // 更新结果
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    private int countAlive(int x, int y) {
        int cnt = 0;
        for (int k = 0; k < 8; k++) {
            int nx = x + dx[k], ny = y + dy[k];
            // 边界校验
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                continue;
            }
            // 如果这个位置为 0，代表当前轮是死的，不需要算进去
            // 如果这个位置为 1，代表当前轮是活得，需要算进去
            // 如果这个位置为 2，代表当前轮是死的（状态10，下一轮是活的），不需要算进去
            // 如果这个位置为 3，代表是当前轮是活的（状态11，下一轮也是活的），需要算进去
            // 偶数是死细胞，不要算进去
            cnt += (board[nx][ny] & 1);
        }

        return cnt;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] board1 = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        solution.gameOfLife(board1);
        System.out.println(Arrays.deepToString(board1)); // [[0, 0, 0], [1, 0, 1], [0, 1, 1], [0, 1, 0]]

        int[][] board2 = {
                {1, 1},
                {1, 0}
        };
        solution.gameOfLife(board2);
        System.out.println(Arrays.deepToString(board2)); // [[1, 1], [1, 1]]
    }
}
