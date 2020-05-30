package LeetCodeSolution.DataStructure._05_StackAndQueue._84_LargestRectangleInHistogram;

import java.util.Stack;

/*
 * 柱状图中最大的矩形
 *
 * 题目描述：
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 思路：
 * 1. 单调栈，从栈底到栈顶依次是由大到小的；
 * 2. 见代码。
 */
public class Solution {

    public int largestRectangleArea(int[] height) {
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
                // k 表示当一个数弹出了，那么它底下的数是啥，
                // 如果这个数弹出后，栈为空了，即它底下没东西了，则左边界 k 就是 -1，
                // 否则，左边界就是弹出之前下面的那个数
                int k = stack.isEmpty() ? -1 : stack.peek();
                // 其实就是 j 往左右两边扩，自己在 j 位置上，左边界来到 k 位置，不能扩展到 k，
                // 右边界来到 i 位置，但不能扩展到 i 位置，
                // 中间所扩展的区间就是 i - k - 1
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }

        // 数组遍历完以后，如果栈中还剩下元素，则开始结算剩下的元素
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            // 右边界统一就是数组的最右边
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 1, 5, 6, 2, 3};

        System.out.println(solution.largestRectangleArea(nums));
    }
}
