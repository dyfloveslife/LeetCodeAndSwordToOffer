package LeetCodeSolution.AlgorithmThought._04_BinarySearch._34_FindFirstAndLastPositionOfElementInSortedArray;

import java.util.Arrays;

/*
 * 在排序数组中查找元素的第一个和最后一个位置
 *
 * 题目描述：
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 思路：
 * 0. 与面试题 53-1 一样，也是采用二分的形式，只不过该题需要返回的是索引；
 * 1. 直观的思路是从前往后遍历一遍数组，用两个变量记录第一次和最后一次 target 的下标，但时间复杂度是 O(n)；
 * 2. 既然数组有序，则可以使用二分查找，实际上应该是使用两次二分，第一次找到 target 的初始位置，第二次找 target 结束位置；
 * 3. 但是在找结束位置时，应该找的是第一个大于 target 的位置再减一；
 * 4. 最终可以分两步：先找第一个大于等于 target 的数，然后再找第一个大于 target 的数。
 */
public class Solution {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int firstPos = binarySearch(nums, target);
        int lastPos = binarySearch(nums, target + 1) - 1;
        if (firstPos == nums.length || nums[firstPos] != target) {
            return new int[]{-1, -1};
        }

        return new int[]{firstPos, lastPos};
    }

    public int binarySearch(int[] nums, int target) {
        // 注意 j 的位置，右边界下标对应的值大于等于目标值
        // 若数组中不存在大于等于目标值，则假设 nums.length 大于等于目标值
        int i = 0, j = nums.length;
        // 循环结束的条件是 i == j，并且 j 对应的值大于等于目标值
        while (i < j) {
            int mid = i + ((j - i) >> 1);
            if (nums[mid] >= target) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }

        return i;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 3, 4, 4, 5, 7};
        int[] nums2 = {5, 7, 7, 8, 8, 10};
        int[] nums3 = {1};
        int[] nums4 = {1, 3};
        int[] nums5 = {};

        System.out.println(Arrays.toString(solution.searchRange(nums1, 8))); // [-1, -1]
        System.out.println(Arrays.toString(solution.searchRange(nums2, 8))); // [3, 4]
        System.out.println(Arrays.toString(solution.searchRange(nums3, 1))); // [0, 0]
        System.out.println(Arrays.toString(solution.searchRange(nums4, 3))); // [1, 1]
        System.out.println(Arrays.toString(solution.searchRange(nums5, 0))); // [-1, -1]
    }
}
