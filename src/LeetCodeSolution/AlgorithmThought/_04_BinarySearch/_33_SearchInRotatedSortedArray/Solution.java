package LeetCodeSolution.AlgorithmThought._04_BinarySearch._33_SearchInRotatedSortedArray;

/*
 * 搜索旋转排序数组
 *
 * 题目描述：
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 思路：
 * 1. 使用二分查找，这里根据 [1, 2, 3, 4, 5, 6, 7] 可以分为两种情况：
 *    1.1) 如果是 [2, 3, 4, 5, 6, 7, 1] 这种情况，即 nums[left] <= nums[middle]，
 *         则可以看出前半部分有序。此时如果 target 位于前半部分，即 nums[left] <= target < nums[middle]：
 *         则在前半部分查找 target，否则在后半部分查找 target。
 *    1.2) 如果是 [6, 7, 1, 2, 3, 4, 5] 这种情况，即 nums[left] >= nums[middle]，
 *         则可以看出后半部分有序。此时如果 target 位于后半部分，即 nums[middle] < target <= nums[right]，
 *         则在后半部分查找 target，否则在前半部分查找 target。
 * 2. 当然，最开始的时候就需要判断 nums[middle] 上的元素是不是 target。
 */
public class Solution {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] == target) {
                return middle;
            }
            // 对应 1.1)
            if (nums[left] <= nums[middle]) {
                if (nums[left] <= target && target < nums[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            // 对应 1.2)
            } else if (nums[left] > nums[middle]) {
                if (nums[middle] < target && target <= nums[right]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int target = 0;
        int[] nums = {4, 5, 6, 7, 0, 1, 2};

        System.out.println(solution.search(nums, target));
    }
}
