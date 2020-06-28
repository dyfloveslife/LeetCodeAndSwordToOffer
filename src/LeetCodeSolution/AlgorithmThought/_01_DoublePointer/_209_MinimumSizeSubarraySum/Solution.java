package LeetCodeSolution.AlgorithmThought._01_DoublePointer._209_MinimumSizeSubarraySum;

/*
 * 长度最小的子数组
 *
 * 题目描述：
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。
 * 如果不存在符合条件的连续子数组，返回 0。
 *
 * 进阶:
 * 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(nlogn) 时间复杂度的解法。
 *
 * 思路：
 * 1. 暴力，需要注意的是，在进行暴力时，当前的 i 也需要进行判断，如果当前的 i 上的元素大于等于 s，则需要返回 -1；
 * 2. 滑动窗口，如果窗口内的和小了，则扩大窗口的右边，如果窗口内的和大了，则缩小窗口的左边；
 * 3. 二分，由于数组是正数，数组中每个元素的前缀和是不断上升的，即呈线性增长的，因此可以使用二分；
 * 4. 因此可以构成一个前缀和数组，它的最小长度为 1，最大长度为数组长度的最大值。
 */
public class Solution {
    public int minSubArrayLen1(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum >= s) {
                return 1;
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s) {
                    res = Math.min(res, j - i + 1);
                }
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public int minSubArrayLen2(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int sum = 0;
        while (right < nums.length) {
            sum += nums[right++];
            while (sum >= s) {
                res = Math.min(res, right - left);
                sum -= nums[left++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public int minSubArrayLen3(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = nums[i] + sum[i];
        }

        int left = 1;
        int right = nums.length;
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            if (check(sum, middle, s)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return check(sum, left, s) ? left : 0;
    }

    // 判断某个长度的子数组的和是否满足大于等于 s 的情况
    private boolean check(int[] sum, int k, int s) {
        for (int i = k; i < sum.length; i++) {
            if (sum[i] - sum[i - k] >= s) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 3, 1, 2, 4, 3};
        int s = 7;

        System.out.println(solution.minSubArrayLen1(s, nums));
        System.out.println(solution.minSubArrayLen2(s, nums));
        System.out.println(solution.minSubArrayLen3(s, nums));
    }
}