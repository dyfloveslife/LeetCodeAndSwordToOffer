package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._287_FindTheDuplicateNumber;

/*
 * 寻找重复数
 *
 * 题目描述：
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设只有一个重复的整数，找出这个重复的数。
 *
 * 说明：
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 * 思路：
 * 1. 直接使用二分，类似于将 3 个苹果放进 2 个抽屉的游戏。
 */
public class Solution {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 1;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            int count = 0;
            for (int num : nums) {
                if (num <= middle) {
                    count++;
                }
            }
            // 由于 while 条件中是小于，因此 right 还需要来到 middle 的位置再次进行判断
            // 由于 count 大于了 middle，说明在 [1, middle] 范围内数字的个数超过了 middle，所以一定有一个重复数字，
            // 因此右边界来到 middle
            if (count > middle) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 3, 4, 2, 2};

        System.out.println(solution.findDuplicate(nums));
    }
}
