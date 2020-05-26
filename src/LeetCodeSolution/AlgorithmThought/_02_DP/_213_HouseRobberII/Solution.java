package LeetCodeSolution.AlgorithmThought._02_DP._213_HouseRobberII;

import java.util.Arrays;

/*
 * 打家劫舍Ⅱ
 *
 * 题目描述：
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例：
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *       偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 思路：
 * 1. 该题和之前的题是一样的，只不过需要将数组分成两部分，分别计算；
 * 2. 第一部分不包括最后一个元素，第二部分不包括第一个元素，然后分别对这两个部分做打家劫舍；
 * 3. 然后再取这两部分的最大值即可。
 */
public class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        return Math.max(myRob(nums, 0, nums.length - 2), myRob(nums, 1, nums.length - 1));
    }

    private int myRob(int[] nums, int start, int end) {
        int prepre = 0;
        int pre = 0;
        int res = 0;
        for (int i = start; i <= end; i++) {
            res = Math.max(prepre + nums[i], pre);
            prepre = pre;
            pre = res;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {2, 3, 2};
        int[] nums2 = {1, 2, 3, 1};

        System.out.println(solution.rob(nums1));
        System.out.println(solution.rob(nums2));
    }
}
