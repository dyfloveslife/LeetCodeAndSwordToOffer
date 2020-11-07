package LeetCodeSolution.AlgorithmThought._07_Sort._327_CountOfRangeSum;

import java.util.Arrays;

/*
 * 区间和的个数
 *
 * 题目描述：
 * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 *
 * 最直观的算法复杂度是 O(n^2) ，请在此基础上优化你的算法。
 *
 * 思路：
 * 1. 使用「前缀和」方法，快速计算区间内的和，但该方法的时间复杂度也是 O(n^2) 的；
 * 2. 在「前缀和」的基础上，使用归并排序的方法，即 https://leetcode-cn.com/problems/count-of-range-sum/solution/qu-jian-he-de-ge-shu-by-leetcode-solution/
 * 3. 也就是在归并排序的 merge 操作之前，统计下标对的数量即可。
 */
public class Solution {
    // 使用「前缀和」
    public int countRangeSum1(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        long[] preSum = new long[nums.length];
        // 计算前缀和
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }

        int ans = 0;
        long cur = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                cur = preSum[j] - (i == 0 ? 0 : preSum[i - 1]);
                if (cur >= lower && cur <= upper) ans++;
            }
        }
        return ans;
    }

    //「前缀和」+ 归并排序
    public int countRangeSum2(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        long[] preSum = new long[nums.length + 1];

        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            preSum[i + 1] = sum;
        }

        return mergeSort(preSum, 0, preSum.length - 1, lower, upper);
    }

    private int mergeSort(long[] preSum, int left, int right, int lower, int upper) {
        if (left >= right) {
            return 0;
        }

        int mid = left + ((right - left) >> 1);
        int n1 = mergeSort(preSum, left, mid, lower, upper);
        int n2 = mergeSort(preSum, mid + 1, right, lower, upper);
        int ans = n1 + n2;

        // 统计下标对的数量
        // 注意：这里区间的范围是 [left, mid] 和 [mid + 1, right]
        // 指针 i 在区间 [left, mid] 内向右滑动
        // 指针 l 和 r 在区间 [mid + 1, right] 内向右滑动
        int i = left;
        int l = mid + 1, r = mid + 1;
        while (i <= mid) {
            while (l <= right && preSum[l] - preSum[i] < lower) l++;
            while (r <= right && preSum[r] - preSum[i] <= upper) r++;
            ans += r - l;
            i++;
        }

        merge(preSum, left, mid, right);

        return ans;
    }

    private void merge(long[] preSum, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int p1 = left, p2 = mid + 1;
        int idx = 0;

        while (p1 <= mid && p2 <= right) {
            help[idx++] = (preSum[p1] <= preSum[p2]) ? (int) preSum[p1++] : (int) preSum[p2++];
        }
        while (p1 <= mid) {
            help[idx++] = (int) preSum[p1++];
        }
        while (p2 <= right) {
            help[idx++] = (int) preSum[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            preSum[left + j] = help[j];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums1 = {-2, 5, -1};
        int lower1 = -2, upper1 = 2;

        int[] nums2 = {-1, 1};
        int lower2 = 0, upper2 = 0;

        int[] nums3 = {0, 0};
        int lower3 = 0, upper3 = 0;

        int[] nums4 = {-2147483647, 0, -2147483647, 2147483647};
        int lower4 = -564, upper4 = 3864;

        System.out.println(solution.countRangeSum1(nums1, lower1, upper1)); // 3
        System.out.println(solution.countRangeSum1(nums2, lower2, upper2)); // 1
        System.out.println(solution.countRangeSum1(nums3, lower3, upper3)); // 3
        System.out.println(solution.countRangeSum1(nums4, lower4, upper4)); // 3
        System.out.println("---");

        System.out.println(solution.countRangeSum2(nums1, lower1, upper1)); // 3
        System.out.println(solution.countRangeSum2(nums2, lower2, upper2)); // 1
        System.out.println(solution.countRangeSum2(nums3, lower3, upper3)); // 3
        System.out.println(solution.countRangeSum2(nums4, lower4, upper4)); // 3
    }
}
