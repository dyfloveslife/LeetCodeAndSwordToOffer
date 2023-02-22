package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._169_MajorityElement;

import java.util.HashMap;
import java.util.Map;

/*
 * 多数元素
 *
 * 题目描述：
 * 给定一个大小为 n 的数组 nums，返回其中的多数元素。多数元素是指在数组中出现次数大于 ⌊n/2⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 *
 * 思路一：使用哈希表计数
 * 1. 其中 key 为当前元素，value 为当前元素所出现的次数；
 * 2. 时间复杂度 O(n)，空间复杂度 O(n)。
 *
 * 思路二：分治
 * 1. 如果把数组分成两部分，那么众数 a 一定是左部分或右部分的众数。
 *
 * 思路三：Boyer-Moore 算法
 * 1. 如果把众数计为 +1，而非众数计为 -1，那么把它们加起来后，由于众数的数量在整个数组中是占比最多的，因此最终的值一定是正的；
 * 2. 首先维护一个候选众数 candidate 以及它出现的次数 count，初始时 candidate 可以为任意值，而 count 为 0；
 * 3. 对于某个元素 num，如果 count == 0，则将 num 赋值给 candidate；
 * 4. 如果 num == candidate，则 count 加 1；否则 count 减 1。
 *
 * 如何理解"Boyer-Moore 多数投票算法"?
 * 1. 可以形象地理解为"同归于尽法"；
 * 2. 第一个到来的士兵，他看到某个高地上没人，那么直接插上自己阵营的旗帜占领这块高地，此时领主 winner（可以理解为变量 candidate）就是这个阵营的人，现存兵力 count = 1；
 * 3. 此时又来了一位士兵，如果该士兵和前一个士兵是同一阵营，则集合起来占领高地，领主不变，winner 依然是当前这个士兵所属阵营，现存兵力 count++；
 * 4. 如果新来到的士兵不是同一阵营，则前方阵营派一个士兵和它同归于尽，此时前方阵营兵力 count--；
 *  （即使双方都死光，这块高地的旗帜 winner 依然不变，因为已经没有活着的士兵可以去换上自己的新旗帜）
 * 5. 当下一个士兵到来，发现前方阵营已经没有兵力，新士兵就成了领主，winner 变成这个士兵所属阵营的旗帜，现存兵力 count++；
 * 6. 最终剩下的必然属于多数阵营的人，也就是 candidate。
 * 7. 参考：https://leetcode.cn/problems/majority-element/solution/duo-shu-yuan-su-by-leetcode-solution/
 */
public class Solution {
    // 思路一
    public int majorityElement1(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int times = n / 2;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > times) {
                return entry.getKey();
            }
        }

        return Integer.MAX_VALUE;
    }

    // 思路二
    public int majorityElement2(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    private int process(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        int mid = left + ((right - left) >> 1);
        // 从左部分子数组中找到众数
        int leftMode = process(nums, left, mid);
        // 从右部分子数组中找到众数
        int rightMode = process(nums, mid + 1, right);
        if (leftMode == rightMode) {
            return leftMode;
        }

        // 如果两部分的众数不是同一元素, 则需要统计其在两部分中出现的次数
        int leftCount = countInRange(nums, leftMode, left, right);
        int rightCount = countInRange(nums, rightMode, left, right);

        return leftCount > rightCount ? leftMode : rightMode;
    }

    private int countInRange(int[] nums, int target, int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] == target) {
                count++;
            }
        }

        return count;
    }

    // 思路三
    public int majorityElement3(int[] nums) {
        int candidate = 0, count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }

            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {3, 2, 3};
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};

        System.out.println(solution.majorityElement1(nums1)); // 3
        System.out.println(solution.majorityElement1(nums2)); // 2

        System.out.println("--");
        System.out.println(solution.majorityElement2(nums1)); // 3
        System.out.println(solution.majorityElement2(nums2)); // 2

        System.out.println("--");
        System.out.println(solution.majorityElement3(nums1)); // 3
        System.out.println(solution.majorityElement3(nums2)); // 2
    }
}
