package LeetCodeSolution.DataStructure._05_StackAndQueue._503_NextGreaterElementII;

import java.util.Arrays;
import java.util.Stack;

/*
 * 下一个更大元素 Ⅱ
 *
 * 题目描述：
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
 * 数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
 * 如果不存在，则输出 -1。
 *
 * 示例：
 * 输入: [1, 2, 1]
 * 输出: [2, -1, 2]
 *
 * 思路：
 * 0. 还是单调栈的应用；
 * 1. 由于是循环数组，因此对于最后一个元素来说，需要找到它的下一个元素，可以通过取余的方式进行；
 * 2. 将结果数组都初始化为 -1，那么对于停留在栈中的元素，说明它是最大元素，没有下一个比它还大的元素；
 * 3. 因此就默认返回的是 -1。
 */
public class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < len * 2; i++) {
            int num = nums[i % len];
            while (!stack.isEmpty() && num > nums[stack.peek()]) {
                res[stack.pop()] = num;
            }
            stack.push(i % len);
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 1};

        System.out.println(Arrays.toString(solution.nextGreaterElements(nums)));
    }
}
