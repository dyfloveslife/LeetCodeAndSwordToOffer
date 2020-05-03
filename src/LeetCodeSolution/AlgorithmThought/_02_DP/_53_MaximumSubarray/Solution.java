package LeetCodeSolution.AlgorithmThought._02_DP._53_MaximumSubarray;

/*
 * 最大子序和
 *
 * 题目描述：
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 思路一：贪心
 * 核心：如果当前指针所指元素之前的和小于 0，则丢失当前元素之前的所有元素；
 *
 * 思路二：DP
 * 1. 核心：如果前一个元素大于 0，则将其加到当前元素上；
 * 2. 还可以判断当前最大连续子序和 sum 与 0 的关系：
 *    如果 sum > 0，说明 sum 对最终的结果有增益，则保留 sum 并加上当前遍历的数字；
 *    否则 sum 对最终结果无增益，则需要舍弃，因此 sum 就变更为当前遍历到的数字；
 * 3. 每次比较 sum 和 res 的大小，输出最大值即可；
 * 4. dp[i] 表示数组中以 nums[i] 结尾的最大子序和，因此
 *    dp[i] = max(dp[i-1]+nums[i], nums[i]).
 */
public class Solution {

    // 贪心
    public int maxSubArray1(int[] nums) {
        int cur = nums[0];
        int max = nums[0];

        for (int num : nums) {
            cur = Math.max(num, cur + num);
            max = Math.max(max, cur);
        }
        return max;
    }

    // DP-1（此方法会对数组中的元素进行修改）
    public int maxSubArray2(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > 0) {
                nums[i] += nums[i - 1];
            }
        }

        // 由于数组中可能只存在一个数，因此需要将 res 设置为数组中第一个数
        int res = nums[0];
        for (int num : nums) {
            res = Math.max(res, num);
        }
        return res;
    }

    // DP-2
    public int maxSubArray3(int[] nums) {
        int res = nums[0];
        int curSum = 0;

        for (int num : nums) {
            if (curSum > 0) {
                curSum += num;
            } else {
                curSum = num;
            }
            res = Math.max(res, curSum);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums2 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums3 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println(solution.maxSubArray1(nums1));
        System.out.println(solution.maxSubArray2(nums2));
        System.out.println(solution.maxSubArray3(nums3));
    }
}
