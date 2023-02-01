package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._26_RemoveDuplicatesFromSortedArray;

/*
 * 删除有序数组中的重复项
 *
 * 题目描述：
 * 给你一个升序排列的数组 nums，请你原地删除重复出现的元素，使每个元素只出现一次，返回删除后数组的新长度。
 * 元素的相对顺序应该保持一致。
 * 将最终结果插入 nums 的前 k 个位置后返回 k。
 * 不要使用额外的空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 分析：
 * 1. 可以使用双指针，由于是升序数组，那么第一个元素已经处于最终的位置了，所以使用 idx 指向第二个元素，也就是可能会被插入的位置；
 * 2. 指针 i 从第二个元素开始往后移动，每次比较当前元素和前一个元素是否相等，若不相等，则将当前元素赋给 idx 位置，然后 idx++；
 * 3. 赋值也是删除元素的一种方式。
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int idx = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                nums[idx++] = nums[i];
            }
        }

        return idx;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 1, 2};
        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

        System.out.println(solution.removeDuplicates(nums1));
        System.out.println(solution.removeDuplicates(nums2));
    }
}
