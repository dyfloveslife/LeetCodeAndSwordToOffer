package SwordToOfferSolution._11_01_MinNumberInRotatedArray;

/*
 * 寻找旋转排序数组中的最小值
 *
 * 题目描述：
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 注意：数组中不存在重复元素。
 *
 * 思路：
 * 1. 直接使用二分法进行查找；
 * 2. 拿 nums[middle] 和 nums[right] 进行比较：
 *    2.1) 如果 nums[middle] < nums[right]，说明 middle 和 right - 1 之内的所有的元素都比 right 小，
 *         则此最小的元素有可能在 nums[middle] 位置上，也有可能在 nums[middle] 的左边，所以需要将 right 定位到 middle 的位置；
 *    2.2) 如果 nums[middle] > nums[right]，说明最小的元素在 middle 的右侧，
 *         因此需要将 left 置为 middle + 1。
 * 3. 由于没有重复的元素，因此不需要判断相等的情况。
 */
public class Solution {

    // 数组中元素不重复
    public int minNumberInRotateArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] < nums[right]) {
                right = middle;
            } else if (nums[middle] > nums[right]) {
                left = middle + 1;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int[] nums2 = {3, 4, 5, 1, 2};

        Solution solution = new Solution();
        System.out.println(solution.minNumberInRotateArray(nums1));
        System.out.println(solution.minNumberInRotateArray(nums2));
    }
}