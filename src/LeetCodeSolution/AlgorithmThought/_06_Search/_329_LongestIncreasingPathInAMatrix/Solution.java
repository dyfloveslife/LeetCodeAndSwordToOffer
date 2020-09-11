package LeetCodeSolution.AlgorithmThought._06_Search._329_LongestIncreasingPathInAMatrix;

/*
 * 矩阵中的最长递增路径
 *
 * 题目描述:
 * 给定一个整数矩阵，找出最长递增路径的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。
 * 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 *
 * 思路：
 * 1. 采用记忆化 dfs 的方式即可，只不过需要在搜索的过程中，将之前已经得到的结果保存下来，避免重复计算；
 * 2. 将矩阵看成一个有向图，如果相邻的两个节点值不相等，则就存在一条从较小值指向较大值的边；
 * 3. 问题就转化成了在有向图中寻找最长路径；
 */
public class Solution {
    public int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    public int rows;
    public int cols;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        rows = matrix.length;
        cols = matrix[0].length;
        int[][] memo = new int[rows][cols];
        int ans = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ans = Math.max(ans, dfs(matrix, i, j, memo));
            }
        }
        return ans;
    }

    private int dfs(int[][] matrix, int row, int col, int[][] memo) {
        // 如果当前位置不等于 0，则说明该单元格之前已经计算过了，
        // 则直接从缓存中读取结果即可
        if (memo[row][col] != 0) {
            return memo[row][col];
        }
        memo[row][col]++;
        // 遍历上、下、左、右四个方向
        for (int[] dir : dirs) {
            int newRow = dir[0] + row;
            int newCol = dir[1] + col;
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                    matrix[newRow][newCol] > matrix[row][col]) {
                memo[row][col] = Math.max(memo[row][col], dfs(matrix, newRow, newCol, memo) + 1);
            }
        }
        return memo[row][col];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};

        System.out.println(solution.longestIncreasingPath(matrix));
    }
}
