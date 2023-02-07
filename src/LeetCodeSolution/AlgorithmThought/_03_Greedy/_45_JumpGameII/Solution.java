package LeetCodeSolution.AlgorithmThought._03_Greedy._45_JumpGameII;

/*
 * 跳跃游戏 Ⅱ
 *
 * 题目描述：
 * 给定一个非负整数数组，你最初位于数组的第一个位置。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 也就是说，给定一个长度为 n 的 0 索引整数数组 nums，初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 *  0 <= j <= nums[i]
 *  i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 *
 * 思路：
 * 0. 需要求最少的跳跃次数；
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
        int steps = 0;
        // 这里需要注意的是，开始的时候边界是第 0 个位置，已经将步数加 1 了，
        // 因此如果是 i < nums.length 的话，那么 i 会来到最后一个位置，
        // 会造成 steps 多加了一次
        for (int i = 0; i < nums.length - 1; i++) {
            // 找到能跳的最远的距离
            maxPos = Math.max(maxPos, i + nums[i]);
            // 更新边界（更新下次能跳到最远的距离）
            if (i == end) {
                end = maxPos;
                steps++;
            }
        }

        return steps;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {2, 3, 1, 1, 4};
        int[] nums2 = {2, 3, 0, 1, 4};

        System.out.println(solution.jump(nums1)); // 2
        System.out.println(solution.jump(nums2)); // 2
    }
}
