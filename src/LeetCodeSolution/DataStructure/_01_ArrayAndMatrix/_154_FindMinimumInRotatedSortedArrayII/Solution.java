package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._154_FindMinimumInRotatedSortedArrayII;

import Other.BasicDataStructure.Knapsack;

/*
 * 寻找旋转排序数组中的最小值 II
 *
 * 题目描述：
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * 给你一个可能存在重复元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的最小元素 。
 * 你必须尽可能减少整个过程的操作步骤。
 *
 * 思路：
 * 1. 使用二分即可，使用 nums[mid] 和 nums[right] 元素进行比较；
 * 2. 由于存在重复元素并且需要找到最小值，所以需要 right 左移。
 */
public class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == nums[right]) {
                right--;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 3, 5};
        int[] nums2 = {2, 2, 2, 0, 1};

        System.out.println(solution.findMin(nums1)); // 1
        System.out.println(solution.findMin(nums2)); // 0
    }
}
