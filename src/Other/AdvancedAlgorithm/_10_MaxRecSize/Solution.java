package Other.AdvancedAlgorithm._10_MaxRecSize;

import java.util.Stack;

/*
 * 求最大子矩阵的大小
 *
 * 题目描述：
 * 给定一个整型矩阵，其中只有 0 和 1 两种值，求其中全是 1 的所有矩形区域中最大的矩形区域为 1 的数量。
 *
 * 例如：
 * 1 0 1 1
 * 1 1 1 1
 * 1 1 1 0
 * 最大矩形区域有 6 个 1，则输出 6。
 *
 * 思路：
 * 将每一行看成直方图，使用直方图的方法进行处理。
 */
public class Solution {
    public static int maxRecSize(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int maxArea = 0;
        // 辅助数组的列数和原矩阵的列数是相同的
        int[] height = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // 当前遍历到的值如果是 1，则直 接加上上一次的 1
                // 如果是 0，则直接变成 0
                height[j] = matrix[i][j] == 0 ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }
        return maxArea;
    }

    // LeetCode-84
    // 柱状图中最大的矩形
    public static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int maxArea = 0;
        // 单调栈，里面存储的是元素的下标
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            // 开始结算
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                // 一个数弹出了，如果弹出后栈为空了，则做左边界 k 就是 -1
                // 否则，左边界就是弹出之前下面的那个数
                int k = stack.isEmpty() ? -1 : stack.peek();
                // 当前数是 i，则 i 就是右边界，curArea 就是延伸的距离
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        // 在都遍历完之后，栈中还剩下的元素再进行出栈
        while (!stack.isEmpty()) {
            int j = stack.pop();
            // 确定左边界 k 的值
            int k = stack.isEmpty() ? -1 : stack.peek();
            // 右边界统一就是数组的最右边
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0}};
        System.out.println(maxRecSize(arr)); // 6
    }
}