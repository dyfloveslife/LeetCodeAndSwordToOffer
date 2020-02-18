package LeetCodeSolution.AlgorithmThought._04_BinarySearch._153_FindMinimumInRotatedSortedArray;

/*
 * 旋转数组的最小数字
 *
 * 题目描述：
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。请找出其中最小的元素。
 * 你可以假设数组中不存在重复元素。
 *
 * 思路：
 * 1. 直接使用二分法进行查找；
 * 2. 拿 nums[middle] 和 nums[right] 进行比较：
 *    2.1) 如果 nums[middle] <= nums[right]，说明 middle 和 right - 1 之内的所有的元素都比 right 小，
 *         则此最小的元素有可能在 nums[middle] 位置上，也有可能在 nums[middle] 的左边，所以需要将 right 定位到 middle 的位置；
 *    2.2) 如果 nums[middle] > nums[right]，说明最小的元素在 middle 的右侧，
 *         因此需要将 left 置为 middle + 1。
 */
public class Solution {
    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] <= nums[right]) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return nums[left];
    }
}
