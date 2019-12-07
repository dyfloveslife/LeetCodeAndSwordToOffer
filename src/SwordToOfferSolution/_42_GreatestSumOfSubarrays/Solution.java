package SwordToOfferSolution._42_GreatestSumOfSubarrays;

/*
 * 连续子数组的最大和
 *
 * 思路一：
 * 遍历数组，如果遇到负值（说明已构不成最大和），则将该值舍弃，否则将该值进行累加；
 * 若当前的累加和比最大和要大的话，则更新最大和。
 *
 * 思路二：DP
 * 1. 定义数组元素的含义：用 dp[i] 表示以第 i 个数字结尾的子数组的最大和；
 * 2. 找出数组元素之间的关系：
 * 2.1 如果 i = 0 或 dp[i-1] <= 0，说明以第 i-1 个数字结尾的子数组的 最大和 小于等于 0，即为负数。则要是把该负数与第 i 个数相加的话，
 * 得到的结果比第 i 个数字本身还要小。所以这种情况下，dp[i] 就等于 array[i]，也就是它本身；
 * 2.2 如果 i ≠ 0 且 dp[i-1] > 0，说明以第 i-1 个数字结尾的子数组的 最大和 大于等于 0，即为正数。则与第 i 个数累加就可以得到
 * 以第 i 个数字结尾的子数组中所有数字的和。在该情况下，dp[i] 就等于 array[i] + dp[i-1]。
 * 3. 找出初始值：
 * 初始值即为数组的第一个数，就是 array[0]。
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
            if (curSum <= 0) {
                curSum = array[i];
            } else {
                curSum += array[i];
            }
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
        int res = array[0]; // 当前所有子数组的和的最大值
        int max = array[0]; // 包含 array[i] 的连续数组最大值
        // 从 1 开始，因为初始值已经考虑了 0 的情况
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max + array[i], array[i]);
            res = Math.max(max, res);
        }
        return res;
    }
}
