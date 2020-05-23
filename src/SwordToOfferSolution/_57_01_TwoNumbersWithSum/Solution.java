package SwordToOfferSolution._57_01_TwoNumbersWithSum;

import java.util.Arrays;
import java.util.HashMap;

/*
 * 和为 s 的两个数字
 *
 * 题目描述：
 * 输入一个递增排序的数组和一个数字 s，在数组中查找两个数，使得它们的和正好是 s。
 * 如果有多对数字的和等于 s，输出任意一对即可。
 *
 * 思路一：对撞的双指针
 * 0. 利用数组是递增排序的性质，可以使用双指针从首尾不断地往中间逼近；
 * 1. 指针 p1 指向数组的第一个元素，指针 p2 指向数组的最后一个元素；
 * 2. 将这两个指针所指的元素进行相加，由于数组已经排好序了：
 *    2.1) 如果 p1 + p2 的值比目标值小，则 p1 向右移动；
 *    2.2) 如果 p1 + p2 的值比目标值大，则 p2 向左移动。
 * 3. 直到找到目标值为止。
 *
 * 思路二：哈希表
 * 1. 将数组中的每个元素放进哈希表中，元素是 key，每个元素出现的次数是 value；
 * 2. 用 target 减去当前遍历到的元素，将结果在哈希表中查询有没有；
 * 3. 有的话返回即可，没有的话遍历下一个元素。
 */
public class Solution {
    // 思路一
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int curSum = nums[left] + nums[right];
            if (curSum == target) {
                return new int[]{nums[left], nums[right]};
            } else if (curSum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }

    // 思路二
    public int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                return new int[]{nums[i], temp};
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 4, 7, 11, 15};

        System.out.println(Arrays.toString(solution.twoSum(nums, 9)));
        System.out.println(Arrays.toString(solution.twoSum2(nums, 9)));
    }
}
