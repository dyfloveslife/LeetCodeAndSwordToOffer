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
 * 1. 使用两次二分查找，第一次找到 target 的第一次出现的位置，第二次找 target+1 第一次出现的位置，以及再往前移动的位置；
 * 2. 在 findIndexOfTarget() 方法中，需要注意 right 的位置，设置成 nums.length 是为了找到 target 在 nums 中的后一个位置
 *    时的准确性。
 */
public class Solution {

    public static int[] searchRange1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int[] res = {-1, -1};
        int firstIndex = getFirst(nums, target, 0, nums.length - 1);
        int lastIndex = getLast(nums, target, 0, nums.length - 1);
        if (firstIndex > -1 && lastIndex > -1) {
            res = new int[]{firstIndex, lastIndex};
        }
        return res;
    }

    private static int getFirst(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int middleIndex = left + ((right - left) >> 1);
        int middleData = nums[middleIndex];

        if (middleData == target) {
            if ((middleIndex > 0 && nums[middleIndex - 1] != target)
                    || middleIndex == 0) {
                return middleIndex;
            } else {
                right = middleIndex - 1;
            }
        } else if (middleData > target) {
            right = middleIndex - 1;
        } else if (middleData < target) {
            left = middleIndex + 1;
        }
        return getFirst(nums, target, left, right);
    }

    private static int getLast(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int middleIndex = left + ((right - left) >> 1);
        int middleData = nums[middleIndex];

        if (middleData == target) {
            if ((middleIndex < right && nums[middleIndex + 1] != target)
                    || middleIndex == right) {
                return middleIndex;
            } else {
                left = middleIndex + 1;
            }
        } else if (middleData < target) {
            left = middleIndex + 1;
        } else if (middleData > target) {
            right = middleIndex - 1;
        }
        return getLast(nums, target, left, right);
    }

    public static int[] searchRange2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int firstIndex = findIndexOfTarget(nums, target);
        int lastIndex = findIndexOfTarget(nums, target + 1) - 1;
        if (firstIndex == nums.length || nums[firstIndex] != target) {
            return new int[]{-1, -1};
        } else {
            return new int[]{firstIndex, Math.max(firstIndex, lastIndex)};
        }
    }

    public static int findIndexOfTarget(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] >= target) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 4, 5, 7};
        int[] arr2 = {5, 7, 7, 8, 8, 10};
        int[] arr3 = {1};
        int[] arr4 = {1, 3};
        System.out.println(Arrays.toString(searchRange1(arr2, 8)));
    }
}
