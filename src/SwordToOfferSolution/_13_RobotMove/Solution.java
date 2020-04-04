package SwordToOfferSolution._13_RobotMove;

/*
 * 机器人走格子
 *
 * 题目描述：
 * 地上有一个 m 行 n 列的方格。一个机器人从坐标 (0, 0) 的格子开始移动，它每一次可以向左、右、上、下移动一格，但不能进入行坐标和列坐标的数位之和大于 k 的格子。
 * 例如，当 k 为 18 时，机器人能够进入方格 (35, 37)，因为 3+5+3+7=18。但它不能进入方格 (35, 38)，因为 3+5+3+8=19。
 * 请问该机器人能够到达多少个格子？
 *
 * 思路：
 * 1. 使用 dfs 或者 bfs，建议使用 bfs，因为在数据量比较大的时候，dfs 有可能造成栈溢出；
 * 2. dfs 用栈，bfs 用队列；
 * 3. 从 (0, 0) 开始，每次扩展符合条件的之前没有扩到的格子；
 * 4. 注意参数的范围：
 *    1 <= n,m <= 100
 *    0 <= k <= 20
 */
public class Solution {
    // DFS
    public int movingCount(int m, int n, int k) {
        if (m < 1 || n < 1 || k < 0) {
            return 0;
        }

        boolean[][] visited = new boolean[m][n];
        return dfs(m, n, k, 0, 0, visited);
    }

    public int dfs(int m, int n, int k, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || getSum(i, j) > k) {
            return 0;
        }

        visited[i][j] = true;
        // 注意: (0, 0) 位置就算是 1 个
        int count = 1;
        count += dfs(m, n, k, i - 1, j, visited)
                + dfs(m, n, k, i + 1, j, visited)
                + dfs(m, n, k, i, j - 1, visited)
                + dfs(m, n, k, i, j + 1, visited);
        return count;
    }

    private int getSum(int i, int j) {
        return getDigitSum(i) + getDigitSum(j);
    }

    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.movingCount(2, 3, 1));
        System.out.println(solution.movingCount(3, 1, 0));
    }
}
