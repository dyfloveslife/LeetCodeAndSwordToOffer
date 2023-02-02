package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._27_RemoveElement;

/*
 * 移除元素
 *
 * 题目描述：
 * 给你一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 分析：
 * 1. 要求"原地移除所有等于 val 的元素"，可以使用双指针的思想，i 指向待插入的位置，j 从 0 位置开始往后遍历数组；
 * 2. 当 nums[j] 遇到与 val 不等的时候，将 j 位置元素赋值给 i 位置元素，同时 i 往后移动即可。
 */
public class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i++] = nums[j];
            }
        }

        return i;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {3, 2, 2, 3};
        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};

        System.out.println(solution.removeElement(nums1, 3));
        System.out.println(solution.removeElement(nums2, 2));
    }
}
