package LeetCodeSolution.DataStructure._04_HashTable._594_LongestHarmoniousSubsequence;

import java.util.HashMap;

/*
 * 最长和谐子序列
 *
 * 题目描述：
 * 和谐数组是指一个数组里元素的最大值和最小值之间的差别正好是 1。
 * 现在，给定一个整数数组，你需要在所有可能的子序列中找到最长的和谐子序列的长度。
 *
 * 思路：
 * 1. 由于差值是 1，因此其实找的就是相邻元素的个数；
 * 2. 可以使用哈希表，key 表示当前元素，value 表示当前元素所出现的次数；
 * 3. 在遍历的时候，如果 当前元素+1 存在哈希表中，则开始计算两者出现的次数，并不断更新最大值。
 */
public class Solution {
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        for (int key : map.keySet()) {
            if (map.containsKey(key + 1)) {
                res = Math.max(res, map.get(key) + map.get(key + 1));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 3, 2, 2, 5, 2, 3, 7};

        System.out.println(solution.findLHS(nums));
    }
}
