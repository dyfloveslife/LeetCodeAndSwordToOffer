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
 * 思路：
 * 0. https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/
 * 1. 需要注意两点的是：下一个数增加的幅度尽可能小，例如 123465，应将 5 和 4 交换，而不是 6 和 4 交换；
 * 2. 第二点是：将 5 和 4 交换完成后，5 后面的数字需要按照升序的顺序进行排序，即 123546，而不是 123564；
 * 3. 算法的流程是：首先从后往前找到一对相邻的且从左往右升序的元素对 nums[i] 和 nums[j]，其中 i < j 且 nums[i] < nums[j]；
 * 4. 然后在 [j, end) 中从后往前找，找到第一个大于 nums[i] 的元素 nums[k]，将 nums[i] 与 nums[k] 交换；
 * 5. 交换完成后，[j, end) 就是必是降序的，然后使其升序即可。
 */
public class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        for (int i = nums.length - 1; i > 0; i--) {
            // 从后往前找，找到一组升序的元素对
            if (nums[i - 1] < nums[i]) {
                Arrays.sort(nums, i, nums.length);
                // 这里和【思路】中描述的不同，
                // 与其从后往前找，不如先排序，再从 [j, end) 中找到第一个比 nums[i - 1] 的元素，然后进行交换
                for (int j = i; j < nums.length; j++) {
                    if (nums[j] > nums[i - 1]) {
                        int temp = nums[j];
                        nums[j] = nums[i - 1];
                        nums[i - 1] = temp;
                        return;
                    }
                }
            }
        }
        // 如果不存在下一个更大的排列，则将数字按照升序排序
        Arrays.sort(nums);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3, 8, 5, 7, 6, 4};

        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
