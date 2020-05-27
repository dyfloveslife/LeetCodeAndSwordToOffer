package LeetCodeSolution.DataStructure._04_HashTable._560_SubarraySumEqualsK;

import java.util.HashMap;

/*
 * 和为 K 的子数组
 *
 * 题目描述：
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 思路：
 * 1. 使用 HashMap，用 key 保存前缀和，用 value 保存 key 所对应的前缀和的个数；
 * 2. 对于下标为 0 的元素，前缀和为 0，且个数为 1；
 * 3. 例如对于数组 [4, 5, 0, -2, -3, 1] 来说，每一位所对应的前缀和为：
 *                [0, 4, 9,  9,  7, 4, 5]
 * 4. 然后使用 map 的 key 存储前缀和，value 存储前缀和出现的次数；
 * 5. 例如，当前缀和为 0 的时候，出现了 1 次；
 * 6. 当前缀和为 4 的时候出现了 2 次；
 * 7. 当前缀和为 9 的时候出现了 2 次；
 * 8. 如果 preSum - k 在 map 中存在的话，则说明我能找到一组和为 k 的子数组。
 */
public class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = 0;
        int preSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int num : nums) {
            preSum += num;
            if (map.containsKey(preSum - k)) {
                res += map.get(preSum - k);
            }
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {4, 5, 0, -2, -3, 1};
        int k = 5;

        System.out.println(solution.subarraySum(nums, k));
    }
}
