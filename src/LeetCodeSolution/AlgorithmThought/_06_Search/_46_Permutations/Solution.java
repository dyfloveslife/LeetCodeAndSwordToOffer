package LeetCodeSolution.AlgorithmThought._06_Search._46_Permutations;

import java.util.ArrayList;
import java.util.List;

/*
 * 全排列
 *
 * 题目描述：
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 思路：
 * 0. 一个数字的全排列有 f(n)=n! 种。
 * 1. 回溯+递归；
 * 2. 回溯模板：
 *    result = []
 *    做选择：result.add(...)
 *    递归：backtrack(...)
 *    撤销选择：result.remove(...)
 * 3. 核心在于：需要在递归之前做选择，而在递归之后撤销选择；
 * 4. 对于 abcd 来说，首先可以选择 abcd，然后回溯为 abc_，由于 d 已经被选择过了，以此再次回溯成 ab_ _，
 *    此时可以进一步选择为 abdc。这就是回溯的过程。
 */
public class Solution {

    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    private boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return ans;
        }

        used = new boolean[nums.length];
        backtrack(nums);
        return ans;
    }

    private void backtrack(int[] nums) {
        // 找到一个排列
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        // 尝试每个数字
        for (int i = 0; i < nums.length; i++) {
            // 如果已经使用过该数字，则跳过
            if (used[i]) {
                continue;
            }

            used[i] = true;
            // 做选择
            path.add(nums[i]);
            // 递归
            backtrack(nums);
            // 撤销选择
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {0, 1};
        int[] nums3 = {1};

        Solution solution = new Solution();
        System.out.println(solution.permute(nums1));
    }
}
