package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._220_ContainsDuplicateIII;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/*
 * 存在重复元素 III
 *
 * 题目描述:
 * 给你一个整数数组 nums 和两个整数 k 和 t。请你判断是否存在两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t，同时又满足 abs(i - j) <= k。
 * 如果存在则返回 true，不存在则返回 false。
 *
 * 思路一：TreeSet
 * 1. 该题可以理解为：对于某元素 nums[i]，希望在索引范围为 [max(0, i - k), i] 内找到值范围在 [nums[i] - t, nums[i] + t] 的数；
 * 2. 可以在索引 [0, i - 1] 内维护一个滑动窗口，该窗口内的元素最好是有序的，对于当前的元素 nums[i]，在滑动窗口内找到与 nums[i] 相关的两个值：
 *  2.1 需要在窗口内找到小于等于 nums[i] 的最大值；
 *  2.2 同时需要找到大于等于 nums[i] 的最小值；
 * 3. 最后需要维护窗口大小；
 * 4. TreeSet 的查找和插入的时间复杂度是 O(log k)，因此总的时间复杂度是 O(nlog k)，空间复杂度是 O(k)。
 *
 * 思路二：桶
 * 1. 思路一的耗时在于"在有序集合中找到与 nums[i] 最接近的数"；
 * 2. 如果能够将 k 个元素分别放到 k 个桶的话，那么就可以在 O(1) 的时间内找到满足的元素；
 * 3. size 设置为 t+1 是为了确保小于等于 t 的元素能够落到同一个桶中，例如 [0,1,2,3]，t = 3，如果 size = 3，那么最终划分的情况为 [0,1,2]、[3] 两个桶；
 * 3. 时间复杂度 O(n)，空间复杂度 O(k)。
 */
public class Solution {
    // 思路一
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Integer left = set.floor(num);
            Integer right = set.ceiling(num);
            // 小于等于 num 且最接近 num 的数
            if (left != null && num - left <= t) {
                return true;
            }
            // 大于等于 num 且最接近 num 的数
            if (right != null && right - num <= t) {
                return true;
            }

            set.add(num);
            // 需要维持窗口大小为 k
            if (i >= k) {
                set.remove(nums[i - k]);
            }
        }

        return false;
    }

    // 思路二
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int size = t + 1;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int idx = getIdx(num, size);
            if (map.containsKey(idx)) {
                return true;
            }
            // 相邻的桶
            int left = idx - 1, right = idx + 1;
            if (map.containsKey(left) && num - map.get(left) <= t) {
                return true;
            }
            if (map.containsKey(right) && map.get(right) - num <= t) {
                return true;
            }
            map.put(idx, num);
            if (i >= k) {
                map.remove(getIdx(nums[i - k], size));
            }
        }

        return false;
    }

    private int getIdx(int num, int size) {
        return num >= 0 ? num / size : ((num + 1) / size) - 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 3, 1};
        int[] nums2 = {1, 0, 1, 1};
        int[] nums3 = {1, 5, 9, 1, 5, 9};

        System.out.println(solution.containsNearbyAlmostDuplicate(nums1, 3, 0)); // true
        System.out.println(solution.containsNearbyAlmostDuplicate(nums2, 1, 2)); // true
        System.out.println(solution.containsNearbyAlmostDuplicate(nums3, 2, 3)); // false

        System.out.println("---");
        System.out.println(solution.containsNearbyAlmostDuplicate1(nums1, 3, 0)); // true
        System.out.println(solution.containsNearbyAlmostDuplicate1(nums2, 1, 2)); // true
        System.out.println(solution.containsNearbyAlmostDuplicate1(nums3, 2, 3)); // false
    }
}
