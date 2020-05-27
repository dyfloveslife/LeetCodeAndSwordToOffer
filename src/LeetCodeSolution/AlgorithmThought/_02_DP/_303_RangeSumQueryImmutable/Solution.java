package LeetCodeSolution.AlgorithmThought._02_DP._303_RangeSumQueryImmutable;

/*
 * 区域和检索-数组不可变
 *
 * 题目描述：
 * 给定一个整数数组 nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i、j 两点。
 *
 * 思路：
 * 1. 例如：原数组 [3, 2, 5,  4,  7]
 *         前缀和  [0, 3, 5, 10, 14, 21]
 *         当前的前缀和 = 前一个前缀和 + 数组中前一个元素
 * 2. 注意，在声明前缀和数组的时候，需要多申请一位，用于数组的开头 0；
 * 3. 如果要求 (1, 3) 内的元素总和，则直接用 preSum[4]-preSum[1] 即可；
 * 4. 因此，如果要求索引从 i 到 j 范围内的和，则只需要用 preSum[j + 1] - preSum[i] 即可；
 * 5. 或者将前缀和声明和原数组一样长度，例如给定原数组 [3, 2, 5,  4,  7]，
 *                                      它的前缀和为 [3, 5, 10, 14, 21]；
 * 6. 如果这样定义的话，想要求 (1, 3) 的总和，则需要使用 preSum[3] - preSum[0]；
 * 7. 即想要求 (i, j) 的总和，使用 preSum[j] - preSum[i - 1]；
 * 8. 但是上式存在一个问题，假如给定 i 的值是 0，那么就会造成越界，因此建议使用 preSum[j + 1] - preSum[i] 的形式。
 */
public class Solution {

    static class NumArray {
        private int[] preSum;

        public NumArray (int[] nums) {
            // 在定义前缀和的时候，多声明一个位置
            preSum = new int[nums.length + 1];
            // i 从 1 开始是因为，preSum[0] 的前缀和就是 0，我们不需要管
            for (int i = 1; i < preSum.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }
        }

        public int sumRange(int i, int j) {
            return preSum[j + 1] - preSum[i];
        }
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);

        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }
}
