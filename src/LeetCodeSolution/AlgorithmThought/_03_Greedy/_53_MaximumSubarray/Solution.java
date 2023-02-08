package LeetCodeSolution.AlgorithmThought._03_Greedy._53_MaximumSubarray;

/*
 * 最大子数组的和
 *
 * 题目描述：
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 分析：
 * 1. 如果 nums 都是正数，那么连续子数组的最大和就等于整个数组元素的累加和；
 * 2. 如果 nums 都是负数，那么连续子数组的最大和就是数组中最大的那个元素；
 * 3. 如果既有正数又有负数，那么可以使用不同的算法进行计算。
 *
 * 思路一：暴力
 * 1. 对于数组中的每个位置，我都求它的子数组的和，最终不断更新那个最大的和；
 * 2. 时间复杂度 n*(n+1)/2，即 O(n^2)，空间复杂度为 O(1)。
 *
 * 思路二：贪心，实际上叫做 Kadane's algorithm
 * 1. 首先对数组进行遍历，当前的最大连续的子数组的最大和记为 curSum，最终的最大和记为 maxSum；
 * 2. 如果 curSum > 0 ，说明 curSum 对结果有增益，则将 curSum 进行保留，并加上当前遍历的元素；
 * 3. 如果 curSum <= 0，说明 curSum 对结果没有增益，需要舍弃，则将 curSum 直接更新为当前遍历的元素；
 * 4. 每次比较 curSum 和 maxSum 的大小，将两者中最大的赋值给 maxSum 并返回；
 * 5. 时间复杂度为 O(n)，因为至少需要遍历一遍数组，空间复杂度为 O(1)。
 */
public class Solution {
    // 思路一
    public int maxSubArray1(int[] nums) {
        // 如果 nums 输入的数据可能为空，那么需要将 ans 初始化为 0，否则应该设置为 Integer.MIN_VALUE
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int curSum = 0;
            for (int j = i; j < nums.length; j++) {
                curSum += nums[j];
                ans = Math.max(ans, curSum);
            }
        }

        return ans;
    }

    // 思路二
    public int maxSubArray2(int[] nums) {
        int curSum = 0, maxSum = Integer.MIN_VALUE;
        for (int num : nums) {
            if (curSum > 0) {
                curSum += num;
            } else {
                curSum = num;
            }
            maxSum = Math.max(curSum, maxSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums2 = {1};
        int[] nums3 = {5, 4, -1, 7, 8};
        int[] nums4 = {-2, -1, -3};

        System.out.println(solution.maxSubArray1(nums1)); // 6
        System.out.println(solution.maxSubArray1(nums2)); // 1
        System.out.println(solution.maxSubArray1(nums3)); // 23
        System.out.println(solution.maxSubArray1(nums4)); // -1
        System.out.println("--");
        System.out.println(solution.maxSubArray2(nums1)); // 6
        System.out.println(solution.maxSubArray2(nums2)); // 1
        System.out.println(solution.maxSubArray2(nums3)); // 23
        System.out.println(solution.maxSubArray2(nums4)); // -1
    }
}
