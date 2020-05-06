package LeetCodeSolution.AlgorithmThought._02_DP._377_Combination_SumIV;

/*
 * 组合总和 Ⅳ
 *
 * 题目描述：
 * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
 * 请注意，顺序不同的序列被视作不同的组合。
 *
 * 思路：
 * 1. 使用 DP，用 dp[i] 表示对于给定的数组，和为 i 的组合的个数，因此 dp[i] += dp[i-num]；
 * 2. 例如 nums=[1, 3, 4]，target = 7：
 *    dp[7] = dp[7 - 1] + dp[7 - 3] + dp[7 - 4]
 *          = dp[6] + dp[4] + dp[3]。
 */
public class Solution {
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3};
        int target = 4;

        System.out.println(solution.combinationSum4(nums, target));
    }
}
