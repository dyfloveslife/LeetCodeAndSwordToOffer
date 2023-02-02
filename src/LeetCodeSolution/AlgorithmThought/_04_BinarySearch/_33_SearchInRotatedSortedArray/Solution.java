package LeetCodeSolution.AlgorithmThought._04_BinarySearch._33_SearchInRotatedSortedArray;

/*
 * 搜索旋转排序数组
 *
 * 题目描述：
 * 整数数组 nums 按升序排列，数组中的值互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]。
 * 例如 [0, 1, 2, 4, 5, 6, 7] 在下标 3 处经旋转后可能变为 [4, 5, 6, 7, 0, 1, 2]。
 * 给你旋转后的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 思路：
 * 0. 题目要求时间复杂度为 O(log n)，则普通遍历的方法时间复杂度为 O(n)，显然不符合题目要求，看到给定的 nums 是排好序的，自然想到了二分；
 * 1. 使用二分查找，这里根据 [1, 2, 3, 4, 5, 6, 7] 可以分为两种情况：
 *    1.1) 如果是 [2, 3, 4, 5, 6, 7, 1] 这种情况，即 nums[left] <= nums[middle]，
 *         则可以看出前半部分有序。此时如果 target 位于前半部分，即 nums[left] <= target < nums[middle]：
 *         则在前半部分查找 target，否则在后半部分查找 target。
 *    1.2) 如果是 [6, 7, 1, 2, 3, 4, 5] 这种情况，即 nums[left] >= nums[middle]，
 *         则可以看出后半部分有序。此时如果 target 位于后半部分，即 nums[middle] < target <= nums[right]，
 *         则在后半部分查找 target，否则在前半部分查找 target。
 * 2. 当然，最开始的时候就需要判断 nums[middle] 上的元素是不是 target；
 * 3. 最重要的还是看旋转数组的第一个元素与中间元素的大小关系。
 */
public class Solution {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int i = 0, j = nums.length - 1;
        while (i <= j) {
            // int mid = i + ((j - i) >> 1);
            int mid = i + (j - i) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[i] <= nums[mid]) {
                if (nums[i] <= target && target <= nums[mid]) {
                    j = mid - 1;
                } else {
                    i = mid + 1;
                }
            } else {
                if (nums[mid] <= target && target <= nums[j]) {
                    i = mid + 1;
                } else {
                    j = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        System.out.println(solution.search(nums1, target1)); // 4

        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;
        System.out.println(solution.search(nums2, target2)); // -1

        int[] nums3 = {1};
        int target3 = 0;
        System.out.println(solution.search(nums3, target3)); // -1
    }
}
