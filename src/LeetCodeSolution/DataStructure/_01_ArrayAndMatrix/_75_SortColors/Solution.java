package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._75_SortColors;

import java.util.Arrays;

/*
 * 颜色分类
 *
 * 题目描述：
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 * 思路：
 * 1. 普通的排序算法不满足时间复杂度的要求，又不能使用内置 sort 函数；
 * 2. 观察给定的数组中只含有 0、1 和 2 三种数字，因此可以用指针将它们划分成三个部分；
 * 3. 使用三个指针，p0 用于维护数字 0 的边界，p2 用户维护数字 2 的边界，i 用于维护数字 1 的边界；
 * 4. 其实 i 相当于当前遍历到的元素。
 */
public class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int p0 = 0, p2 = nums.length - 1;
        int i = 0;
        while (i <= p2) {
            if (nums[i] == 0) {
                swap(nums, i++, p0++);
            } else if (nums[i] == 1) {
                i++;
            } else {
                swap(nums, i, p2--);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        int[] nums2 = {2, 0, 1};

        solution.sortColors(nums1);
        System.out.println(Arrays.toString(nums1)); // [0, 0, 1, 1, 2, 2]

        solution.sortColors(nums2);
        System.out.println(Arrays.toString(nums2)); // [0, 1, 2]
    }
}
