package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._80_RemoveDuplicatesFromSortedArrayII;

/*
 * 删除有序数组中的重复项 II
 *
 * 题目描述：
 * 给你一个有序数组 nums，请你原地删除重复出现的元素，使得出现次数超过两次的元素只出现两次，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组，并在使用 O(1) 额外空间的条件下完成。
 *
 * 思路：
 * 1. 该题可以抽象为「保留 K 位相同的元素」；
 * 2. 对于前 K 个相同的数字，可以直接保留，而对于后面任意的数字，只能在"与前面写入的位置之前的第 K 个元素进行比较时，其值不同"的情况下才能保留。
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        return process(nums, 2);
    }

    private int process(int[] nums, int k) {
        // idx 代表当前需要被替换的位置
        int idx = 0;
        for (int num : nums) {
            // 若 nums[idx - k] 等于 num，则说明已经出现了 K+1 个相同的元素
            // 此时 idx 不变，而是继续往后遍历 num
            // 此刻的 idx 指向的就是待"删除"的元素
            if (idx < k || nums[idx - k] != num) {
                nums[idx++] = num;
            }
        }

        return idx;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int[] nums2 = {0, 0, 1, 1, 1, 1, 2, 3, 3};

        System.out.println(solution.removeDuplicates(nums1)); // 5
        System.out.println(solution.removeDuplicates(nums2)); // 7
    }
}
