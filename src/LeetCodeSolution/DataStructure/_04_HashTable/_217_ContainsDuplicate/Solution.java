package LeetCodeSolution.DataStructure._04_HashTable._217_ContainsDuplicate;

import java.util.HashSet;

/*
 * 存在重复元素
 *
 * 题目描述：
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果任意一值在数组中出现至少两次，函数返回 true。
 * 如果数组中每个元素都不相同，则返回 false。
 *
 * 思路：
 * 1. 使用 HashSet 解决。
 */
public class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            } else {
                set.add(num);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 3, 1};
        int[] nums2 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};

        System.out.println(solution.containsDuplicate(nums1));
        System.out.println(solution.containsDuplicate(nums2));
    }
}
