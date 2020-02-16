package LeetCodeSolution.AlgorithmThought._03_Greedy._665_NonDecreasingArray;

/*
 * 非递减数组
 *
 * 题目描述：
 * 给定一个长度为 n 的整数数组，你的任务是判断在最多改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * 我们是这样定义一个非递减数列的：对于数组中所有的 i (1 <= i < n)，满足 array[i] <= array[i + 1]。
 *
 * 思路：
 * 1. 假设 nums[i-1] > nums[i]，例如 [2,3,7, 6 ,9,12]，需要考虑修改哪个数才能保证不影响后序的顺序；
 *    此时，应修改 nums[i-1] = nums[i];如果修改成 nums[i] = nums[i-1] 的话，nums[i-1]有可能比 nums[i+1]要大；
 * 2. 假设 nums[i-2] > nums[i]，例如 [8,9,10, 6 ,12]，则只能修改成 nums[i] = nums[i-1]。
 */
public class Solution {
    public static boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int count = 0;
        // 因为这里只看 i 前面的元素情况，不看后面的元素，所以索引从 1 开始
        for (int i = 1; i < nums.length && count < 2; i++) {
            // 已经是升序了，则直接判断下一位
            if (nums[i] >= nums[i - 1]) {
                continue;
            }

            // 程序能够执行到这里，说明需要改变元素
            count++;
            // i - 2 >= 0 保证不越界，此时 i 最小等于 2，说明 i 前面还有两个元素
            if (i - 2 >= 0 && nums[i - 2] > nums[i]) {
                nums[i] = nums[i - 1];
            } else {
                nums[i - 1] = nums[i];
            }
        }
        return count < 2;
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 3};
        System.out.println(checkPossibility(arr));
    }
}
