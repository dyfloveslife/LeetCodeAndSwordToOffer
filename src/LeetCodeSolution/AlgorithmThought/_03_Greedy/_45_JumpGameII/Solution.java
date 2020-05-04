package LeetCodeSolution.AlgorithmThought._03_Greedy._45_JumpGameII;

/*
 * 跳跃游戏Ⅱ
 *
 * 题目描述：
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 思路：
 * 1. 还是使用贪心的方法，每次在可跳范围内选择可以跳的更远的位置；
 * 2. 用 end 表示当前能够到达的边界，在遍历数组的时候，每当到达了边界之后再更新新的边界。
 */
public class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int end = 0;
        int maxPos = 0;
        int res = 0;
        // 这里需要注意的是，开始的时候边界是第 0 个位置，已经将步数加 1 了，
        // 因此如果是 i < nums.length 的话，那么 i 会来到最后一个位置，
        // 会造成 res 多加了一次
        for (int i = 0; i < nums.length - 1; i++) {
            // 找到能跳的最远的距离
            maxPos = Math.max(maxPos, i + nums[i]);
            // 更新边界
            if (i == end) {
                end = maxPos;
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 3, 1, 1, 4};

        System.out.println(solution.jump(nums));
    }
}
