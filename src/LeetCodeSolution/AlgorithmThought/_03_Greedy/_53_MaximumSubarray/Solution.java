package LeetCodeSolution.AlgorithmThought._03_Greedy._53_MaximumSubarray;

/*
 * 最大子数组的和
 *
 * 题目描述：
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 思路：
 * 1. 首先对数组进行遍历，当前的最大连续的子数组的最大和记为 curSum，最终的最大和记为 maxSum；
 * 2. 如果 curSum > 0 ，说明 curSum 对结果有增益，则将 curSum 进行保留，并加上当前遍历的元素；
 * 3. 如果 curSum <= 0，说明 curSum 对结果没有增益，需要舍弃，则将 curSum 直接更新为当前遍历的元素；
 * 4. 每次比较 curSum 和 maxSum 的大小，将两者中最大的赋值给 maxSum 并返回。
 */
public class Solution {
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (curSum > 0) {
                curSum += nums[i];
            } else {
                curSum = nums[i];
            }
            maxSum = Math.max(curSum, maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(arr));
    }
}
