package SwordToOfferSolution._29_PrintMatrix;

import java.util.ArrayList;

/**
 * 顺时针打印矩阵
 */
public class Solution {
	public ArrayList<Integer> printMatrix(int[][] matrix) {
		ArrayList<Integer> list = new ArrayList<>();
		int row = matrix.length;
		int col = matrix[0].length;

		if (matrix == null || row <= 0 || col <= 0) return list;

		// 定义左上和右下两个点
		int left = 0, top = 0, right = col - 1, bottom = row - 1;

		while (left <= right && top <= bottom) {
			// left to right
			for (int i = left; i <= right; i++) list.add(matrix[top][i]); // or matrix[left][i]

			// top to bottom
			for (int i = top + 1; i <= bottom; i++) list.add(matrix[i][right]);

			// 以下两种情况需要考虑到单行或者单列的情况
			// right to left
			if (top != bottom) {
				for (int i = right - 1; i >= left; i--) list.add(matrix[bottom][i]);
			}

			// bottom to top
			// i 只大于 top，是因为之前访问过了
			if (left != top) {
				for (int i = bottom - 1; i > top; i--) list.add(matrix[i][left]); // or matrix[i][top]
			}

			left++;
			right--;
			top++;
			bottom--;
		}

		return list;
	}
}
