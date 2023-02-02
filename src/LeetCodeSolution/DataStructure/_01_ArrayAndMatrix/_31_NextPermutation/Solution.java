package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._31_NextPermutation;

import java.util.Arrays;

/*
 * 下一个排列
 *
 * 题目描述：
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * 思路一：Brute Force
 * 如果用暴力的方法将所有的排列找到的话，那么时间复杂度是 O(n!)，空间复杂度是 O(n)。
 *
 * 思路二：
 * 1. 分析
 *  1.1 https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/
 *  1.2 https://en.wikipedia.org/wiki/Permutation#Generation_in_lexicographic_order
 * 2. 以 12385764 为例，其下一个更大的排列为 12386457；
 * 3. 首先从后往前找到一对相邻的且从左往右升序的元素对 nums[i] 和 nums[i + 1]，其中 nums[i] < nums[i + 1]，这里为 5 和 7；
 * 5. 然后在 [i + 1, end) 中从后往前找，找到第一个大于 nums[i] 的元素 nums[j]，这个元素是 6，即 j 为 6；
 * 6. 将 nums[i] 与 nums[j] 交换，即将 5 和 6 交换，此时的序列为 12386754；
 * 7. 交换完成后，[i + 1, end) 必然是降序的，即 754 是将序的，然后使其升序即可。
 */
public class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int i = nums.length - 2;
        // 从后往前找到一对相邻的且从左往右升序的元素对
        // 注意第二个表达式的"大于等于"号
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // 给定的 nums 不是最后一个排列
        if (i >= 0) {
            // 从后往前找，找到第一个大于 nums[i] 的元素 nums[k]
            int j = nums.length - 1;
            while (nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }

        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int j = nums.length - 1;
        while (start < j) {
            swap(nums, start++, j--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 3, 8, 5, 7, 6, 4};

        solution.nextPermutation(nums1);
        System.out.println(Arrays.toString(nums1));

        int[] nums2 = {1, 2, 3};
        solution.nextPermutation(nums2);
        System.out.println(Arrays.toString(nums2));

        int[] nums3 = {3, 2, 1};
        solution.nextPermutation(nums3);
        System.out.println(Arrays.toString(nums3));

        int[] nums4 = {1, 1, 5};
        solution.nextPermutation(nums4);
        System.out.println(Arrays.toString(nums4));
    }
}
