package LeetCodeSolution.AlgorithmThought._04_BinarySearch._35_SearchInserrPosition;

/*
 * 搜索插入位置
 *
 * 题目描述：
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 * 思路：
 * 1. 看到有序数组和 O(long n)，首先想到使用二分；
 * 2. 与普通二分的区别在于，该题有一个要求是"如果目标值不存在于数组中，返回它将会被按顺序插入的位置"；
 * 3. 其实需要注意二分最后返回的 i，如果没有找到 target 的话，i 最终会指向 target 左侧元素的位置，此时需要返回 i + 1；
 * 4. 这个 i + 1 就可以满足"如果目标值不存在于数组中，返回它将会被按顺序插入的位置"；
 * 5. 但如果 while 中的条件为 i <= j 的话，那么其实就可以省略步骤 i + 1，因为当 i == j 的时候，指针还会移动一次。
 */
public class Solution {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int mid = i + ((j - i) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }

        return i;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 3, 5, 6};

        System.out.println(solution.searchInsert(nums, 5)); // 2
        System.out.println(solution.searchInsert(nums, 2)); // 1
        System.out.println(solution.searchInsert(nums, 7)); // 4
        System.out.println(solution.searchInsert(nums, 0)); // 0
    }
}
