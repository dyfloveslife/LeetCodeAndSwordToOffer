package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._81_SearchInRotatedSortedArrayII;

/*
 * 搜索旋转排序数组 II
 *
 * 题目描述：
 * 已知存在一个按非降序排列的整数数组 nums，数组中的值不必互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标从 0 开始计数）。
 * 例如，[0, 1, 2, 4, 4, 4, 5, 6, 6, 7] 在下标 5 处经旋转后可能变为 [4, 5, 6, 6, 7, 0, 1, 2, 4, 4]。
 * 给你旋转后的数组 nums 和一个整数 target，请你编写一个函数来判断给定的目标值是否存在于数组中。
 * 如果 nums 中存在这个目标值 target，则返回 true，否则返回 false。
 * 你必须尽可能减少整个操作步骤。
 *
 * 思路：
 * 1. 与「搜索旋转排序数组」的方式相同，但是「搜索旋转排序数组 II」存在重复元素；
 * 2. 在给定重复元素的数组中使用二分法，会遇到 nums[left] == nums[mid] == nums[right] 的情况；
 * 3. 此时无法确定区间 [left, mid] 和 [mid + 1, right] 哪个是有序的；
 * 4. 因此需要分别修改 left 和 right 的值，进而划定新的区间；
 * 5. 此外，需要注意给定数组长度为 1 的情况。
 */
public class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return nums[0] == target;
        }

        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int mid = i + ((j - i) >> 1);
            if (target == nums[mid]) {
                return true;
            }
            if (nums[i] == nums[mid] && nums[mid] == nums[j]) {
                i++;
                j--;
                // [i, mid] 是有序的
            } else if (nums[mid] >= nums[i]) {
                if (nums[i] <= target && target <= nums[mid]) {
                    j = mid - 1;
                } else {
                    i = mid + 1;
                }
            // [mid + 1, j] 是有序的
            } else {
                if (nums[mid] <= target && target <= nums[j]) {
                    i = mid + 1;
                } else {
                    j = mid - 1;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {2, 5, 6, 0, 0, 1, 2};
        int[] nums2 = {2, 5, 6, 0, 0, 1, 2};
        int[] nums3 = {1, 0, 1, 1, 1};
        int[] nums4 = {1};
        int[] nums5 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 13, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        System.out.println(solution.search(nums1, 0)); // true
        System.out.println(solution.search(nums2, 3)); // false
        System.out.println(solution.search(nums3, 0)); // true
        System.out.println(solution.search(nums4, 0)); // false
        System.out.println(solution.search(nums5, 13)); // true
    }
}
