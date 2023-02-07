package LeetCodeSolution.AlgorithmThought._03_Greedy._55_JumpGame;

/*
 * 跳跃游戏
 *
 * 题目描述：
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 *
 * 思路：
 * 1. 使用贪心的方法，从每一个位置开始跳，如果能跳到最后的位置，那就返回 true；
 * 2. 直接计算每个位置能够跳的最远的距离，记录下来；
 * 3. 然后从全局来看，最远的距离是不是能够到达数组最后的位置或超过数组最后的位置；
 * 4. 也就是说，对于数组中的每一个元素来说，我都计算一下它能够到达的最远位置，用 max 保存；
 * 5. 然后遍历数组的下一个位置，再次计算它能够到达的最远位置，更新 max；
 * 6. 最后返回 max 是否到达或者超过了数组的长度即可。
 */
public class Solution {
    public boolean canJump1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            // if 中的表达式可以这么理解：因为 max 表示最多能够走到的长度，i 表示索引
            // 只有 i 在 max 范围内才能继续往后走，否则将无法到达最后一个元素
            if (i <= max) {
                // 当前的位置 i 再加上可以走的距离 nums[i]
                max = Math.max(max, i + nums[i]);
            }
        }

        return max >= nums.length - 1;
    }

    public boolean canJump2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int step = 0;
        for (int i = 0; i < nums.length; i++) {
            // if 中的表达式意味着当前位置再也无法跳跃到最后一个元素
            if (i > step) {
                return false;
            }

            step = Math.max(step, i + nums[i]);
        }

        // 如果可以一直跳到最后，就返回 true
        return true;
    }

    public boolean canJump3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int step = 0;
        for (int i = 0; i <= step; i++) {
            // 第 i 个元素能够跳到的最远距离
            int temp = i + nums[i];
            // 更新最远距离
            step = Math.max(step, temp);
            // 如果 step 已经超过了数组中最后一个元素，则提前返回
            if (step >= nums.length - 1) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {2, 3, 1, 1, 4};
        int[] nums2 = {3, 2, 1, 0, 4};
        int[] nums3 = {0};

        System.out.println(solution.canJump1(nums1)); // true
        System.out.println(solution.canJump1(nums2)); // false
        System.out.println(solution.canJump1(nums3)); // true
        System.out.println("----");
        System.out.println(solution.canJump2(nums1)); // true
        System.out.println(solution.canJump2(nums2)); // false
        System.out.println(solution.canJump2(nums3)); // true
        System.out.println("----");
        System.out.println(solution.canJump3(nums1)); // true
        System.out.println(solution.canJump3(nums2)); // false
        System.out.println(solution.canJump3(nums3)); // true
    }
}
