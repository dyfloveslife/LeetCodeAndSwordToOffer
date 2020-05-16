package SwordToOfferSolution._42_GreatestSumOfSubarrays;

/*
 * 连续子数组的最大和
 *
 * 题目描述：
 * 输入一个整型数组，数组里有正数也有负数。数组中一个或连续的多个整数组成一个子数组。
 * 求所有子数组的和的最大值。要求时间复杂度为 O(n)。
 *
 * 思路一：贪心
 * 1. 首先对数组进行遍历，当前的最大连续的子数组的最大和记为 curSum，最终的最大和记为 curSum；
 * 2. 如果 curSum > 0 ，说明 curSum 对结果有增益，则将 curSum 进行保留，并加上当前遍历的元素；
 * 3. 如果 curSum <= 0，说明 curSum 对结果没有增益，需要舍弃，则将 curSum 直接更新为当前遍历的元素；
 * 4. 每次比较 curSum 和 curSum 的大小，将两者中最大的赋值给 curSum 并返回。
 *
 * 思路二：DP
 * 1. 定义数组元素的含义：用 dp[i] 表示以第 i 个数字结尾的子数组的最大和；
 * 2. 找出数组元素之间的关系：
 *   2.1) 如果 i = 0 或 dp[i-1] <= 0，说明以第 i-1 个数字结尾的子数组的 最大和 小于等于 0，即为负数。则要是把该负数与第 i 个数相加的话，
 *        得到的结果比第 i 个数字本身还要小。所以这种情况下，dp[i] 就等于 nums[i]，也就是它本身；
 *   2.2) 如果 i ≠ 0 且 dp[i-1] > 0，说明以第 i-1 个数字结尾的子数组的 最大和 大于等于 0，即为正数。则与第 i 个数累加就可以得到
 *        以第 i 个数字结尾的子数组中所有数字的和。在该情况下，dp[i] 就等于 nums[i] + dp[i-1]。
 * 3. 找出初始值：
 *    初始值即为数组的第一个数，就是 nums[0]。
 * 4. 所以，最后需要求的就是 dp[i] = max(nums[i], nums[i] + dp[i-1])，也就是求花括号里面这两部分的最大值。
 * 5. 由于求解 dp[i] 只依赖 dp[i-1]，因此可以使用变量来存储。
 *
 * 说明：
 * 1. 其实可以从简单的方面考虑，用 dp 的话，将 dp[i] 看做以 nums[i] 结尾的情况下连续子数组的最大和；
 * 2. 从索引为 1 开始，如果 dp[i - 1] 大于 0，则 dp[i] 就等于之前的最大和 dp[i - 1] 再加上当前的 nums[i]；
 *                    如果 dp[i - 1] 小于等于 0，则说明 dp[i - 1] 对 dp[i] 产生负贡献，dp[i - 1] + nums[i] 还不如 nums[i] 本身大。
 */
public class Solution {

    // 使用 DP 数组，缺点是空间复杂度为 O(N)
    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        // 注意 res 的初始值
        int res = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
        }

        for (int num : dp) {
            res = Math.max(res, num);
        }
        return res;
    }

    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // res 表示当前的最大值，通过不断地更新它，最后将其返回
        int res = nums[0];
        int curSum = 0;

        for (int i = 0; i < nums.length; i++) {
            // 对结果有增益
            if (curSum > 0) {
                curSum += nums[i];
            } else {
                curSum = nums[i];
            }
            res = Math.max(res, curSum);
        }
        return res;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums2 = {-1};

        System.out.println(solution.maxSubArray1(nums1));
        System.out.println(solution.maxSubArray1(nums2));

        System.out.println(solution.maxSubArray2(nums1));
        System.out.println(solution.maxSubArray2(nums2));
    }
}
