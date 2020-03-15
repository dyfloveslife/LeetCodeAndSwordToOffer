package Other.BasicAlgorithm._33_Islands;

import java.util.LinkedList;
import java.util.Queue;

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
 * 思路 1：dfs
 * 1. 如果是单 CPU、单机器的情况下，即矩阵数量不多的情况；
 * 2. 可以遍历矩阵中的每个数，在遍历的时候调用一个“感染”函数，如果当前的数是 1 ，则将它与它相邻的数都变为 2，然后岛的数量加一；
 * 3. 然后继续遍历判断下一个数，遇到 2 则往后遍历，直至结束。
 * 4. 从某一点 (i, j) 的上下左右做深度遍历；
 * 5. 终止条件：
 *        (i, j) 越过矩阵边界；
 *        matrix[i][j] == 0，表示此分支已经越过岛屿边界。
 * 6. 在搜索岛屿的同时，执行 matrix[i][j]=2，即将岛屿所有节点删除，避免之后重复搜索相同的岛屿；
 *
 * 思路 2：bfs
 * 1. 类似于 dfs，不同点在于搜索岛屿边界的方法不同；
 * 2. 使用一个队列，判断队首元素 (i, j) 是否未越界且为 1：
 *      若是，则置零（删除岛屿），并将当前元素的上下左右元素入队；
 *      否则跳过当前元素；
 * 3. 循环 pop 队列首元素，直到队列是空为止，此时已经遍历完此岛屿。
 *
 * 思路 3：
 * 1. 如果是多 CPU、多机器情况下，即矩阵数量很多的情况；
 * 2. 如果将矩阵拆分成多块，每块交给不同的机器去处理，需要考虑到如何将每块进行结合起来；
 * 3. 其实，可以统计每块岛的数量，然后只需要关心块与块之间的边界信息即可；
 * 4. 边界之间的数值如果都是 1 的话，则岛的数量应该减一，但是应做到不能重复减；
 * 5. 这里就可以使用并查集的方法。
 */
public class Solution {

    // dfs（岛的数量）
    public static int countIsLands(int[][] matrix) {
        if (matrix == null || matrix[0] == null) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    res++;
                    dfs(matrix, i, j);
                }
            }
        }
        return res;
    }

    private static void dfs(int[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] == 0) {
            return;
        }

        matrix[i][j] = 0;
        dfs(matrix, i + 1, j);
        dfs(matrix, i - 1, j);
        dfs(matrix, i, j + 1);
        dfs(matrix, i, j - 1);
    }

    // bfs（岛的数量）
    public static int numsIslandsBFS(int[][] arr) {
        if (arr == null || arr[0] == null) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 1) {
                    bfs(arr, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static void bfs(int[][] arr, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            i = cur[0];
            j = cur[1];
            if (i >= 0 && i < arr.length && j >= 0 && j < arr[0].length && arr[i][j] == 1) {
                arr[i][j] = 0;
                queue.offer(new int[]{i + 1, j});
                queue.offer(new int[]{i - 1, j});
                queue.offer(new int[]{i, j - 1});
                queue.offer(new int[]{i, j + 1});
            }
        }
    }


    // 最大的岛面积（DFS）
    public static int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid[0].length == 0) {
            return -1;
        }

        int res = 0;
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfsMaxArea(grid, i, j));
                }
            }
        }
        return res;
    }

    private static int dfsMaxArea(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }

        // 开始的时候，岛的数量初始化为 1
        int count = 1;
        grid[i][j] = 0;
        count += dfsMaxArea(grid, i + 1, j);
        count += dfsMaxArea(grid, i - 1, j);
        count += dfsMaxArea(grid, i, j + 1);
        count += dfsMaxArea(grid, i, j - 1);
        return count;
    }

    public static void main(String[] args) {
        int[][] m1 = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0},
        };
        int[][] m2 = {
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
        };
        System.out.println(numsIslandsBFS(m1));
        System.out.println(maxAreaOfIsland(m1));
    }
}
