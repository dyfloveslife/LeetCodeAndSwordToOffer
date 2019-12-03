package SwordToOfferSolution._13_RobotMove;

/*
 * 机器人走格子
 */
public class Solution {
	public int movingCount(int threshold, int rows, int cols) {
		if (threshold < 0 || rows <= 0 || cols <= 0) return 0;
		boolean[][] visited = new boolean[rows][cols];
		int count = movingCountCore(threshold, rows, cols, 0, 0, visited);
		return count;
	}

	private int movingCountCore(int threshold, int rows, int cols, int row, int col, boolean[][] visited) {
		int count = 0;
		if (check(threshold, rows, cols, row, col, visited)) {
			visited[row][col] = true;

			count = 1 + movingCountCore(threshold, rows, cols, row - 1, col, visited) +
					movingCountCore(threshold, rows, cols, row + 1, col, visited) +
					movingCountCore(threshold, rows, cols, row, col - 1, visited) +
					movingCountCore(threshold, rows, cols, row, col + 1, visited);
		}
		return count;
	}

	/*
	 * 检查机器人能否进入 (row, col) 的方格
	 */
	private boolean check(int threshold, int rows, int cols, int row, int col, boolean[][] visited) {
		if (row >= 0 && row < rows && col >= 0 && col < cols && getDigitSum(row) + getDigitSum(col) <= threshold && !visited[row][col])
			return true;
		return false;
	}

	/*
	 * 计算数位之和
	 */
	private int getDigitSum(int num) {
		int sum = 0;
		while (num > 0) {
			sum += num % 10;
			num /= 10;
		}
		return sum;
	}
}
