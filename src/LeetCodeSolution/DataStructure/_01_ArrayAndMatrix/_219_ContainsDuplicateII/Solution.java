package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._219_ContainsDuplicateII;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * 存在重复元素 II
 *
 * 题目描述：
 * 给你一个整数数组 nums 和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，满足 nums[i] == nums[j] 且 abs(i - j) <= k。
 * 如果存在，返回 true；否则，返回 false。
 *
 * 思路一：HashMap
 * 1. 其中 key 存储元素，value 存储当前元素的索引；
 * 2. 时间复杂度 O(n)，空间复杂度 O(n)。
 *
 * 思路二：滑动窗口 (HashSet)
 * 1. 使用 HashSet 维护大小为 k 的窗口；
 * 2. 当出现重复值时，则说明在 k 范围内存在重复元素；
 * 3. 每次将当前的元素加入到 Set 中，如果 Set 的 size 大于 k，则移除窗口最左侧的元素。
 */
public class Solution {
    // 思路一
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (map.containsKey(nums[i]) && (i - map.get(num) <= k)) {
                return true;
            }
            map.put(nums[i], i);
        }

        return false;
    }

    // 思路二
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            // 如果 set 中包含当前元素
            // 则说明满足"存在两个不同的索引 i 和 j，满足 nums[i] == nums[j] 且 abs(i - j) <= k"这一条件
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 3, 1};
        int[] nums2 = {1, 0, 1, 1};
        int[] nums3 = {1, 2, 3, 1, 2, 3};

        System.out.println(solution.containsNearbyDuplicate1(nums1, 3)); // true
        System.out.println(solution.containsNearbyDuplicate1(nums2, 1)); // true
        System.out.println(solution.containsNearbyDuplicate1(nums3, 2)); // false

        System.out.println("---");
        System.out.println(solution.containsNearbyDuplicate2(nums1, 3)); // true
        System.out.println(solution.containsNearbyDuplicate2(nums2, 1)); // true
        System.out.println(solution.containsNearbyDuplicate2(nums3, 2)); // false
    }
}
