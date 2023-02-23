package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._189_RotateArray;

import java.util.Arrays;

/*
 * 轮转数组
 *
 * 题目描述：
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 *
 * 思路：
 * 1. 首先翻转数组 nums，然后在 k 位置将数组分成两部分，最后分别对这两部分进行翻转即可。
 */
public class Solution {
    public void rotate1(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) {
            return;
        }

        int n = nums.length;
        int[] help = new int[n];
        for (int i = 0; i < n; i++) {
            help[(i + k) % n] = nums[i];
        }

        System.arraycopy(help, 0, nums, 0, n);
    }

    public void rotate2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) {
            return;
        }

        int n = nums.length;
        // k 有可能超过数组的长度
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;

        int[] nums2 = {-1, -100, 3, 99};
        int k2 = 2;

        int[] nums3 = {1, 2, 3};
        int k3 = 2;

        Solution solution = new Solution();
        solution.rotate2(nums1, k1);
        System.out.println(Arrays.toString(nums1)); // [5, 6, 7, 1, 2, 3, 4]
        solution.rotate2(nums2, k2);
        System.out.println(Arrays.toString(nums2)); // [3, 99, -1, -100]
        solution.rotate2(nums3, k3);
        System.out.println(Arrays.toString(nums3)); // [2, 3, 1]
    }
}
