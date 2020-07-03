package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._485_MaxConsecutiveOnes;

/*
 * 最大连续 1 的个数
 *
 * 题目描述：
 * 给定一个二进制数组， 计算其中最大连续 1 的个数。
 *
 * 思路：
 * 1. 刚开始的思路是使用双指针来解决；
 * 2. 不过对于方法二，实现起来更加简单一些。
 */
public class Solution {

    public int findMaxConsecutiveOnes1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int i = 0;
        int j = 0;
        int res = 0;
        while (j < nums.length) {
            if (nums[j] == 1) {
                j++;
                continue;
            }
            res = Math.max(res, j - 1 - i + 1);
            j++;
            i = j;
        }
        if (j >= nums.length) {
            res = Math.max(res, j - 1 - i + 1);
        }
        if (nums.length == 1) {
            return nums[j - 1] == 1 ? 1 : 0;
        }
        return res;
    }


    public int findMaxConsecutiveOnes2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = 0;
        int cur = 0;
        for (int num : nums) {
            cur = num == 0 ? 0 : cur + 1;
            res = Math.max(res, cur);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 1, 0, 1, 1, 1};

        System.out.println(solution.findMaxConsecutiveOnes1(nums));
        System.out.println(solution.findMaxConsecutiveOnes2(nums));
    }
}
