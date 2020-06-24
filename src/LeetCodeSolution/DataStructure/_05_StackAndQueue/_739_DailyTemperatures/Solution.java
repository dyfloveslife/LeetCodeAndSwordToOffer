package LeetCodeSolution.DataStructure._05_StackAndQueue._739_DailyTemperatures;

import java.util.Arrays;
import java.util.Stack;

/*
 * 每日温度
 *
 * 题目描述：
 * 根据每日气温列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。
 * 如果之后都不会升高，请在该位置用 0 来代替。
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 思路一：
 * 1. 最容易想到的是对于每个当前的元素，我都从它的后面找比当前元素大的元素，并记录数量；
 * 2. 但这样的时间复杂度很高。
 *
 * 思路二：
 * 1. 典型的单调栈的应用，直接看代码中的注释。
 */
public class Solution {

    public int[] dailyTemperatures1(int[] nums) {
        if (nums == null) {
            return null;
        }

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // 开始结算
                if (nums[j] > nums[i]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }

    public int[] dailyTemperatures2(int[] nums) {
        if (nums == null) {
            return null;
        }

        int[] res = new int[nums.length];
        // 栈中存储的是元素的索引
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            // 开始结算，弹出谁，就开始结算谁，
            // 而对应的结果是谁让栈顶出栈的，那么这个结果就是它俩之间的差值
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                int k = stack.pop();
                res[k] = i - k;
            }
            stack.push(i);
        }
        return res;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {73, 74, 75, 71, 69, 72, 76, 73};

        System.out.println(Arrays.toString(solution.dailyTemperatures1(nums)));
        System.out.println(Arrays.toString(solution.dailyTemperatures2(nums)));
    }
}
