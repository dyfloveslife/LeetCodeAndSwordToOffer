package LeetCodeSolution.AlgorithmThought._08_Mathematics._136_SingleNumber;

/*
 * 只出现一次的数字
 *
 * 题目描述：
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。
 * 找出那个只出现了一次的元素。
 *
 * 思路:
 * 1. 一个数与自己进行异或的结果是 0；
 * 2. 一个数与 0 进行异或的结果是该数。
 */
public class Solution {
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {4, 1, 2, 1, 2};

        System.out.println(solution.singleNumber(nums));
    }
}
