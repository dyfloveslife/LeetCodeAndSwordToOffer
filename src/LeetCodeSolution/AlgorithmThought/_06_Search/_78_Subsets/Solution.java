package LeetCodeSolution.AlgorithmThought._06_Search._78_Subsets;

import java.util.ArrayList;
import java.util.List;

/*
 * 子集
 *
 * 题目描述：
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集），包含子集为空的情况。
 * 说明：解集不能包含重复的子集。
 *
 * 思路：
 * 回溯。
 */
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        List<Integer> path = new ArrayList<>();
        dfs(res, path, nums, 0);
        return res;
    }

    public void dfs(List<List<Integer>> res, List<Integer> path, int[] nums, int start) {
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(res, path, nums, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {1, 2, 3};
        System.out.println(s.subsets(nums));
    }
}
