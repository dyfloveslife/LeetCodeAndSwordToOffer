package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._280_WiggleSort;

import java.util.Arrays;

/*
 * 摆动排序
 *
 * 题目描述：
 * 给定一个未排序的数组 nums，使用如下排序规则原地排序：
 * nums[0] <= nums[1] >= nums[2] <= nums[3] >= nums[4]...
 *
 * 思路：
 * 1. 这里可以直接对原数组进行排序，然后交换第 2 个和第 3 个元素，再继续交换第 4 个和第 5 个元素，时间复杂度 O(n lgn)；
 * 2. 通过观察可以看到：
 *     当 i 为奇数时，需要满足 nums[i - 1] <= nums[i]；
 *     当 i 为偶数时，需要满足 nums[i - 1] >= nums[i]；
 *     时间复杂度 O(n)；
 * 3. 需要考虑相邻两个元素相等的场景。
 */
public class Solution {
    public void wiggleSort1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        Arrays.sort(nums);
        for (int i = 2; i < nums.length; i += 2) {
            swap(nums, i, i - 1);
        }
    }

    public void wiggleSort2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        for (int i = 1; i< nums.length; i++) {
            if ((i % 2 == 1 && nums[i - 1] > nums[i]) || (i % 2 == 0 && nums[i - 1] < nums[i])) {
                swap(nums, i - 1, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 5, 2, 1, 6, 4};
        int[] nums2 = {3, 5, 2, 1, 6, 4};

        Solution solution = new Solution();
        solution.wiggleSort1(nums1);
        solution.wiggleSort2(nums2);

        System.out.println(Arrays.toString(nums1)); // [1, 3, 2, 5, 4, 6]
        System.out.println(Arrays.toString(nums2)); // [3, 5, 1, 6, 2, 4]
    }
}
