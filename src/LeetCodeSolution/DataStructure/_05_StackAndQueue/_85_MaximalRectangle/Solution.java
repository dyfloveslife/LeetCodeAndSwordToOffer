package LeetCodeSolution.DataStructure._05_StackAndQueue._85_MaximalRectangle;

import java.util.Stack;

/*
 * 最大矩形
 *
 * 题目描述：
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 思路：
 * 和 84 题一样，只不过该题分别对每一行做一次 84 题的操作。
 */
public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int[] arr = new int[matrix[0].length];
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                arr[j] = (matrix[i][j] == '0') ? 0 : arr[j] + 1;
            }
            res = Math.max(res, process(arr));
        }
        return res;
    }

    public int process(int[] nums) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] <= nums[stack.peek()]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int area = (i - k - 1) * nums[j];
                res = Math.max(res, area);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int area = (nums.length - k - 1) * nums[j];
            res = Math.max(res, area);
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] matrix = {{'1', '0', '1', '0', '0'},
                        {'1', '0', '1', '1', '1'},
                        {'1', '1', '1', '1', '1'},
                        {'1', '0', '0', '1', '0'}};

        System.out.println(solution.maximalRectangle(matrix));
    }
}
