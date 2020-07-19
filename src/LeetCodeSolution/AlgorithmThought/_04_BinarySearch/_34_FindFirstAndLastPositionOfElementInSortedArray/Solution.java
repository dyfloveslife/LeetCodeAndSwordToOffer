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
 * 1. 使用两次二分查找，第一次找到 target 的初始位置，第二次找 target 结束位置；
 * 2. 再找结束位置时，需要将 middle 的位置再加 1，以方便查找。
 */
public class Solution {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int firstPosition = getFirstPosition(nums, target);
        // 如果在找 target 时，本身就没有出现在数组中，则直接返回 [-1, -1] 即可
        if (firstPosition == -1) {
            return new int[]{-1, -1};
        }

        int lastPosition = getLastPosition(nums, target);
        return new int[]{firstPosition, lastPosition};
    }

    private int getFirstPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] == target) {
                right = middle;
            } else if (nums[middle] > target) {
                right = middle - 1;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }

    private int getLastPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // 注意这里的加 1 操作
            int middle = 1 + left + ((right - left) >> 1);
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] == target) {
                left = middle;
            } else if (nums[middle] > target) {
                right = middle - 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 3, 4, 4, 5, 7};
        int[] nums2 = {5, 7, 7, 8, 8, 10};
        int[] nums3 = {1};
        int[] nums4 = {1, 3};

        System.out.println(Arrays.toString(solution.searchRange(nums1, 8)));
        System.out.println(Arrays.toString(solution.searchRange(nums2, 8)));
        System.out.println(Arrays.toString(solution.searchRange(nums3, 0)));
        System.out.println(Arrays.toString(solution.searchRange(nums4, 3)));

    }
}
