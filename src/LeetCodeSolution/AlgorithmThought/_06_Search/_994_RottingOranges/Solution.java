package LeetCodeSolution.AlgorithmThought._06_Search._994_RottingOranges;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 腐烂的橘子
 *
 * 题目描述：
 * 在给定的网格中，每个单元格可以有以下三个值之一：
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。
 * 如果不可能，返回 -1。
 *
 * 思路：
 * 1. 使用队列，先把腐烂的橘子依次入队；
 * 2. 然后从队头弹出一个腐烂的橘子，同时将它周围的橘子进行腐蚀，然后再将腐蚀后的橘子入队，此时 time++；
 * 3. 最后，如果 grid 中还有新鲜的橘子的话，那么就返回 -1。
 */
public class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        // 定义上下左右四个方向
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> queue = new LinkedList<>();
        // 首先将 grid 中所有腐烂的橘子入队，
        // 队列中存储的是腐烂橘子的坐标，因此用数组实现
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        // 下面就是 bfs 的模板
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int j = 0; j < dirs.length; j++) {
                    int x = cur[0] + dirs[j][0];
                    int y = cur[1] + dirs[j][1];
                    // 如果上下左右移动的方向在允许的范围内，则将其中的新鲜的橘子进行腐烂操作，
                    // 然后将刚刚被腐蚀的橘子加入到队列中
                    if (check(grid, x, y)) {
                        grid[x][y] = 2;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
            if (!queue.isEmpty()) {
                ans++;
            }
        }
        // 最后检查一下 grid 中还有没有新鲜的橘子
        for (int[] arr : grid) {
            for (int num : arr) {
                if (num == 1) {
                    return -1;
                }
            }
        }
        return ans;
    }

    // 如果 i 和 j 没有越界，并且当前的 grid[i][j] 是新鲜的橘子的话，则返回 true
    private boolean check(int[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};

        System.out.println(solution.orangesRotting(grid));
    }
}
