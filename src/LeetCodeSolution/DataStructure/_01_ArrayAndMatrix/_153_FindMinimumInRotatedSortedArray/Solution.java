package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._153_FindMinimumInRotatedSortedArray;

/*
 * 寻找旋转排序数组中的最小值
 *
 * 题目描述：
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次旋转后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 思路：
 * 1. 这里所谓的"旋转"，其实是将数组看作一个圈，顺时针旋转后得到的结果；
 * 2. 看到数组是排序的，并且时间复杂度也要求是 O(log n)，第一时间想到使用二分；
 * 3. 但是由于给定的是"旋转"后的数组，无法直接使用标准的二分查找；
 * 4. 通过分析示例发现，其实需压找到一个拐点，该拐点左侧所有的元素均大于数组的第一个元素，拐点右侧的所有元素均小于数组的第一个元素；
 * 5. 例如数组 [4,5,6,7,0,1,2] 中的拐点在 7 和 0 之间；
 * 6. 首先找到 nums[mid]，如果 nums[mid] > 数组中第一个元素，则说明拐点在 mid 的右侧；
 * 7. 如果 nums[mid] < 数组中第一个元素，则我们需要从 mid 的左侧去找拐点；
 * 8. 满足什么条件才能停止呢？若 nums[mid] > nums[mid + 1]，则 mid + 1 是最小值；若 nums[mid - 1] > nums[mid]，则 mid 就是最小值；
 * 9. 例如 [4,5,6,7,2,3]，left 指向 7，mid 指向 2，right 指向 3。
 */
public class Solution {
    public int findMin1(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int left = 0, right = nums.length - 1;
        if (nums[right] > nums[0]) {
            return nums[0];
        }

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return Integer.MAX_VALUE;
    }

    public int findMin2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {3, 4, 5, 1, 2};
        int[] nums2 = {4, 5, 6, 0, 1, 2};
        int[] nums3 = {11, 13, 15, 17};
        int[] nums4 = {3, 1, 2};

        System.out.println(solution.findMin1(nums1)); // 1
        System.out.println(solution.findMin1(nums2)); // 0
        System.out.println(solution.findMin1(nums3)); // 11
        System.out.println(solution.findMin1(nums4)); // 1

        System.out.println("---");
        System.out.println(solution.findMin2(nums1)); // 1
        System.out.println(solution.findMin2(nums2)); // 0
        System.out.println(solution.findMin2(nums3)); // 11
        System.out.println(solution.findMin2(nums4)); // 1
    }
}
