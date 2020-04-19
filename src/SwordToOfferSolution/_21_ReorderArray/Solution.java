package SwordToOfferSolution._21_ReorderArray;

import java.util.Arrays;

/*
 * 调整数组顺序使奇数位于偶数前面
 *
 * 题目描述：
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 *
 * 思路：
 * 1. 使用双指针，left 从左向右寻找偶数，right 从右向左寻找奇数；
 * 2. 将 left 和 right 所指的元素进行交换；
 * 3. 直到 left 和 right 相遇位置；
 * 4. 也可以使用快慢指针，一个走两格，一个走一格。
 */
public class Solution {

    // 双指针
    public int[] exchange1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            while (left < right && nums[left] % 2 == 1) left++;
            while (left < right && nums[right] % 2 == 0) right--;
            swap(nums, left, right);
        }
        return nums;
    }

    public int[] exchange2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) {
                swap(nums, i, start);
                start++;
            }
        }
        return nums;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3, 4};

        System.out.println(Arrays.toString(solution.exchange1(nums)));
        System.out.println(Arrays.toString(solution.exchange2(nums)));
    }
}
