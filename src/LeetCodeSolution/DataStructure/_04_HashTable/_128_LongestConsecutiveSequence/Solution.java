package LeetCodeSolution.DataStructure._04_HashTable._128_LongestConsecutiveSequence;

import java.util.Arrays;
import java.util.HashMap;

/*
 * 最长连续序列
 *
 * 题目描述：
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 *
 * 思路一：
 * 1. 首先将数组排序，这会导致时间复杂度是 O(nlogn) 的；
 * 2. 遍历当前数组，如果相邻两个数的差值等于 1，说明它俩满足连续，因此需要将当前的长度加 1；
 * 3. 如果差值不等于 1，则说明它俩不相邻，因此需要进行结算，然后维护最大长度。
 *
 * 思路二：
 * 1. 哈希表；
 * 2. key 存储的是当前遍历到的元素，value 存储的是当前最长连续序列的长度；
 * 3. 例如 [1,2,3,4,5]，map.get(1) 和 map.get(4) 返回的都是 5；
 * 4. 每次从 map 得到当前元素 num 的左右相邻元素的 value，即 num-1 的 value，num+1 的 value；
 * 5. 由于 num 加入到了 map 中，所以总的序列的长度需要更新，即 sum=left+1+right；
 * 6. 这个 sum 就是当前 num 的最长连续序列的长度；
 * 7. 最后需要更新边界，避免影响当前 num 的最长连续序列的长度。
 * 8. 也就是说，对于新加入的元素 num，它希望左邻居告诉它，左边能提供的长度，希望右邻居告诉它，右边能提供的长度；
 * 9. 再加上它自己的长度，它就知道自己处在的连续序列的长度；
 * 10. 如果当前元素已经存在于 map 中了，则略过。
 */
public class Solution {
    // O(nlogn)
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        int max = 1;
        int cur = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i - 1] + 1 == nums[i]) {
                    cur++;
                } else {
                    // 如果两个相邻的元素不相等，则开始结算，
                    // 先取最大值，然后由于遇到了不相等的两个相邻数，
                    // 因此，再将当前的最长连续序列的长度置为 1
                    max = Math.max(max, cur);
                    cur = 1;
                }
            }
        }
        return Math.max(max, cur);
    }

    // O(n)
    public int longestConsecutive1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            // 如果 map 中不包含 num 这个数，则开始处理 num 的左右边界
            if (!map.containsKey(num)) {
                // 因为是连续的数，所以这里进行减一和加一操作，即取当前元素的前一个和后一个元素
                int left = map.getOrDefault(num - 1, 0);
                int right = map.getOrDefault(num + 1, 0);
                // 求当前 num 的左右边界长度，即 num 所在序列的长度
                int sum = left + right + 1;
                map.put(num, sum);

                res = Math.max(res, sum);

                // 更新边界
                map.put(num - left, sum);
                map.put(num + right, sum);
            } else {
                // 如果 map 中已经包含了 num，则略过
                continue;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {100, 4, 200, 1, 2, 3};

        System.out.println(solution.longestConsecutive(nums));
        System.out.println(solution.longestConsecutive1(nums));
    }
}
