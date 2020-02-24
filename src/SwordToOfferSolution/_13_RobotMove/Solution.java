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
 * 1. 使用 dfs 或者 bfs，但这里建议使用 bfs，因为在数据量比较大的时候，dfs 有可能造成栈溢出；
 * 2. dfs 用栈，bfs 用队列；
 * 3. 从 (0, 0) 开始，每次扩展符合条件的之前没有扩到的格子；
 */
public class Solution {
    public static int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0 || rows < 0 || cols < 0) {
            return 0;
        }

        boolean[][] visited = new boolean[rows][cols];
        int count = movingCountCore(threshold, rows, cols, 0, 0, visited);
        return count;
    }

    private static int movingCountCore(int threshold, int rows, int cols, int row, int col, boolean[][] visited) {
        int count = 0;
        if (check(threshold, rows, cols, row, col, visited)) {
            // 能进来说明当前格子符合条件，则将其置为 true，指明访问过了
            visited[row][col] = true;
            count = 1 + movingCountCore(threshold, rows, cols, row - 1, col, visited)
                    + movingCountCore(threshold, rows, cols, row + 1, col, visited)
                    + movingCountCore(threshold, rows, cols, row, col - 1, visited)
                    + movingCountCore(threshold, rows, cols, row, col + 1, visited);
        }
        return count;
    }

    // 检查机器人能否进入 (row, col) 的方格
    // 能进入下一个格子的条件是：
    // 1) 格子坐标数位之和不大于 threshold
    // 2) 从坐标 (0, 0) 通过上下左右移动可以到达当前格子
    private static boolean check(int threshold, int rows, int cols, int row, int col, boolean[][] visited) {
        if (row >= 0 && row < rows && col >= 0 && col < cols
                && getDigitSum(row, col) <= threshold
                && !visited[row][col]) {
            return true;
        }
        return false;
    }

    public static int getDigitSum(int i, int j) {
        return getX(i) + getX(j);
    }

    // 计算数位之和
    private static int getX(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(movingCount(1, 2,3));
    }
}
