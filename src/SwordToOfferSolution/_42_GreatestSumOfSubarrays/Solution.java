package SwordToOfferSolution._42_GreatestSumOfSubarrays;

/*
 * 连续子数组的最大和
 *
 * 题目描述：
 * 输入一个整型数组，数组里有正数也有负数。数组中一个或连续的多个整数组成一个子数组。
 * 求所有子数组的和的最大值。要求时间复杂度为 O(n)。
 *
 * 思路一：贪心
 * 1. 首先对数组进行遍历，当前的最大连续的子数组的最大和记为 curSum，最终的最大和记为 maxSum；
 * 2. 如果 curSum > 0 ，说明 curSum 对结果有增益，则将 curSum 进行保留，并加上当前遍历的元素；
 * 3. 如果 curSum <= 0，说明 curSum 对结果没有增益，需要舍弃，则将 curSum 直接更新为当前遍历的元素；
 * 4. 每次比较 curSum 和 maxSum 的大小，将两者中最大的赋值给 maxSum 并返回。
 *
 * 思路二：DP
 * 1. 定义数组元素的含义：用 dp[i] 表示以第 i 个数字结尾的子数组的最大和；
 * 2. 找出数组元素之间的关系：
 *   2.1) 如果 i = 0 或 dp[i-1] <= 0，说明以第 i-1 个数字结尾的子数组的 最大和 小于等于 0，即为负数。则要是把该负数与第 i 个数相加的话，
 *        得到的结果比第 i 个数字本身还要小。所以这种情况下，dp[i] 就等于 array[i]，也就是它本身；
 *   2.2) 如果 i ≠ 0 且 dp[i-1] > 0，说明以第 i-1 个数字结尾的子数组的 最大和 大于等于 0，即为正数。则与第 i 个数累加就可以得到
 *        以第 i 个数字结尾的子数组中所有数字的和。在该情况下，dp[i] 就等于 array[i] + dp[i-1]。
 * 3. 找出初始值：
 *    初始值即为数组的第一个数，就是 array[0]。
 * 4. 所以，最后需要求的就是 dp[i] = max(array[i], array[i] + dp[i-1])，也就是求花括号里面这两部分的最大值。
 * 5. 由于求解 dp[i] 只依赖 dp[i-1]，因此可以使用变量来存储。
 */
public class Solution {
    // 方法一
    public int findGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            // 对结果有增益
            if (curSum > 0) {
                curSum += array[i];
            } else {
                curSum = array[i];
            }
            // 可简写为： maxSum = Math.max(curSum, maxSum);
            if (curSum > maxSum) {
                maxSum = curSum;
            }
        }
        return maxSum;
    }

    // 方法二
    public int findGreatestSumOfSubArray2(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        // 当前所有子数组的和的最大值
        int res = array[0];
        // 包含 array[i] 的连续数组最大值
        int max = array[0];
        // 从 1 开始，因为初始值已经考虑了 0 的情况
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max + array[i], array[i]);
            res = Math.max(max, res);
        }
        return res;
    }
}
