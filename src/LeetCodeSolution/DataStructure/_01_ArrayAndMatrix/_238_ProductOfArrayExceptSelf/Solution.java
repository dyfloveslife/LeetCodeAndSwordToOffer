package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._238_ProductOfArrayExceptSelf;

import java.util.Arrays;

/*
 * 除自身以外数组的乘积
 *
 * 题目描述：
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 进阶：你可以在常数空间复杂度内完成这个题目吗？（出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * 思路：
 * 1. 当前位置的结果 = 左部分元素的成绩 * 右部分元素的成绩；
 * 2. 原数组：       [1       2       3       4]
 *    左部分的乘积：  1       1      1*2    1*2*3
 *    右部分的乘积：2*3*4    3*4      4      1
 *    结果：       1*2*3*4  1*3*4   1*2*4  1*2*3*1
 */
public class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int[] res = new int[nums.length];

        int left = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                left = left * nums[i - 1];
            }
            // 将乘积结果放到数组左侧对应的位置
            res[i] = left;
        }

        int right = 1;
        // 为什么从后往前遍历呢？
        // 因为前面的已经计算过了，如果从前往后的话，那么之前计算过的那些结果就不能使用了
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i < nums.length - 1) {
                right = right * nums[i + 1];
            }
            // 将原来位置上已经乘得的结果，再与新得到的 right 值进行相乘
            res[i] *= right;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3, 4};

        System.out.println(Arrays.toString(solution.productExceptSelf(nums)));
    }
}
