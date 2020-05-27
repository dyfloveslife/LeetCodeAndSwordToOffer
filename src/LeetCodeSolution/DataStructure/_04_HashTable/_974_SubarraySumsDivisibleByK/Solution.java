package LeetCodeSolution.DataStructure._04_HashTable._974_SubarraySumsDivisibleByK;

/*
 * 和可被 K 整除的子数组
 *
 * 题目描述：
 * 给定一个整数数组 A，返回其中元素之和可被 K 整除的子数组（连续、非空）的数目。
 *
 * 思路：
 * 1. 一看到“连续子数组的和”，就应该想到前缀和；
 * 2. sum[i] = nums[0] + nums[1] + nums[2] + ... + nums[i]；
 * 3. 某个子数组（从 i 到 j）的和可以表示为 nums[i] + ... + nums[j] = sum[j] - sum[i - 1]；
 * 4. 判断子数组的和能否被 k 整除，就等价于判断 (sum[j] - sum[i - 1]) mod k == 0；
 * 5. 也就等价于判断 sum[j] % k == sum[i - 1] % k。
 */
public class Solution {
    // 暴力，但会超时
    public int subarraysDivByK1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int start = i; start <= j; start++) {
                    sum += nums[start];
                }
                if (sum % k == 0) {
                    res++;
                }
            }
        }
        return res;
    }

    // 前缀和
    public int subarraysDivByK2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] map = new int[k];
        map[0] = 1;
        int preSum = 0;
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            preSum = (preSum + nums[i]) % k;
            if (preSum < 0) {
                preSum += k;
            }
            res += map[preSum];
            map[preSum]++;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {4, 5, 0, -2, -3, 1};
        int k = 5;

        System.out.println(solution.subarraysDivByK1(nums, 5));
        System.out.println(solution.subarraysDivByK2(nums, 5));
    }
}
