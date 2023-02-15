package LeetCodeSolution.DataStructure._04_HashTable._128_LongestConsecutiveSequence;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * 最长连续序列
 *
 * 题目描述：
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 *
 * 思路一：排序
 * 1. 首先将数组排序，这会导致时间复杂度是 O(NlogN)；
 * 2. 遍历当前数组，如果相邻两个数的差值等于 1，说明它俩满足连续，因此需要将当前的长度加 1；
 * 3. 如果差值不等于 1，则说明它俩不相邻，因此需要进行结算，然后维护最大长度。
 *
 * 思路二：哈希表
 * 1. 空间换时间；
 * 2. key 存储的是当前遍历到的元素，value 存储的是当前最长连续序列的长度；
 * 3. 例如 Map 的 key 为 5，value 为 3，则存在包含 5 且长度为 3 的连续区间，例如 [3,4,5]、[4,5,6]、[5,6,7]；
 * 4. 每次从 map 得到当前元素 num 的左右相邻元素的 value，即 num - 1 的 value 和 num + 1 的 value；
 * 5. 由于 num 加入到了 map 中，所以总的序列的长度需要更新，即 sum = left + 1 + right；
 * 6. 这个 sum 就是当前 num 的最长连续序列的长度；
 * 7. 最后需要更新边界，避免影响当前 num 的最长连续序列的长度。
 * 8. 也就是说，对于新加入的元素 num，它希望左邻居告诉它左边能提供的长度，希望右邻居告诉它右边能提供的长度，再加上它自己的长度，它就知道自己处在的连续序列的长度；
 * 9. 如果当前元素已经存在于 map 中了，则略过。
 */
public class Solution {
    // 思路一
    public int longestConsecutive1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        int max = 1, cur = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] - nums[i - 1] == 1) {
                cur++;
            } else {
                cur = 1;
            }
            max = Math.max(max, cur);
        }

        return max;
    }

    // 思路二
    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            if (map.containsKey(num)) {
                continue;
            }
            // 如果 map 中不包含 num 这个数，则开始处理 num 的左右边界
            // 因为是连续的数，所以这里进行减一和加一操作，即取当前元素的前一个和后一个元素
            int left = map.getOrDefault(num - 1, 0);
            int right = map.getOrDefault(num + 1, 0);
            // 求当前 num 的左右边界长度，即 num 所在序列的长度
            int sum = left + right + 1;
            ans = Math.max(ans, sum);
            // 用于标记当前的 num 已经被遍历过了，其对应的 value 可以设置任意值
            map.put(num, sum);

            // 更新边界
            map.put(num - left, sum);
            map.put(num + right, sum);
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {100, 4, 200, 1, 2, 3};
        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int[] nums3 = {1, 2, 0, 1};
        int[] nums4 = {4, 0, -4, -2, 2, 5, 2, 0, -8, -8, -8, -8, -1, 7, 4, 5, 5, -4, 6, 6, -3};

        System.out.println(solution.longestConsecutive1(nums1)); // 4
        System.out.println(solution.longestConsecutive1(nums2)); // 9
        System.out.println(solution.longestConsecutive1(nums3)); // 3
        System.out.println(solution.longestConsecutive1(nums4)); // 5

        System.out.println("--");
        System.out.println(solution.longestConsecutive2(nums1)); // 4
        System.out.println(solution.longestConsecutive2(nums2)); // 9
        System.out.println(solution.longestConsecutive2(nums3)); // 3
        System.out.println(solution.longestConsecutive2(nums4)); // 5
    }
}
