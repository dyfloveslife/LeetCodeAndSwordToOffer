package LeetCodeSolution.AlgorithmThought._06_Search._1091_ShortestPathInBinaryMatrix;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 二进制矩阵中的最短路径
 *
 * 题目描述：
 * 0 表示可以经过某个位置，求解从左上角到右下角的最短路径长度。
 *
 * 思路：
 * 1. 采用 BFS+队列 的思想，先把顶点放进队列中，然后将与顶点相关的顶点都放进队列中，
 * 2. 当取出的顶点为右下角的点时，则结束，再返回。
 * 3. BFS 模板：
 *    void BFS() {
 *      定义队列；
 *      定义备忘录，用于记录已经访问的位置；
 *      判断边界条件，是否能直接返回结果的；
 *      将起始位置加入到队列中，同时更新备忘录；
 *      while (队列不为空) {
 *          获取当前队列中的元素个数。
 *          for (元素个数) {
 *              取出一个位置节点。
 *              判断是否到达终点位置。
 *              获取它对应的下一个所有的节点。
 *              条件判断，过滤掉不符合条件的位置。
 *              新位置重新加入队列。
 *           }}}
 * 4. 八个方向的定义：
 *    以 (0, 0) 位置出发，分别记录上、下、左、右、左上、左下、右上、右下这 8 个方向。
 *
 *    (-1, -1)  (-1, 0)  (-1, 1)
 *    (0,  -1)  (0,  0)  (0,  1)
 *    (1,  -1)  (1,  0)  (1,  1)
 */
public class Solution {
    public static int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;
        // 对矩阵中的起始位置和终止位置做判断
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return -1;
        }
        // 定义 8 个方向
        int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        // 创建队列，保存每个格子可以走的路
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});

        int pathLen = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            pathLen++;
            // 每次取出队头的元素，进行判断
            while (size-- > 0) {
                int[] data = queue.poll();
                // x 和 y 代表当前格子的位置
                int x = data[0];
                int y = data[1];
                // 如果当前格子是 1，则说明不能走，则越过去
                if (grid[x][y] == 1) {
                    continue;
                }
                // 如果位置已经来到了终点，则将最短畅通路径的长度返回
                if (x == m - 1 && y == n - 1) {
                    return pathLen;
                }
                // 能够来到这里，说明当前的格子不是 1，并且当前格子还没有来到终点位置
                grid[x][y] = 1; // 标记
                // 上面的 (x, y) 已经走完了，也标记完了，那么我就开始走下一个格子
                for (int[] d : dir) {
                    // (x, y) 移动到了 (x1, y1) 位置
                    int x1 = x + d[0];
                    int y1 = y + d[1];
                    // 如果越界，则跳过
                    if (x1 < 0 || x1 >= m || y1 < 0 || y1 >= n) {
                        continue;
                    }
                    // 在不越界的情况下，将下一步可以走的格子添加到队列
                    queue.offer(new int[]{x1, y1});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] arr = {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        int[][] arr2 = {{0, 1}, {1, 0}};
        System.out.println(shortestPathBinaryMatrix(arr));
        System.out.println(shortestPathBinaryMatrix(arr2));
    }
}