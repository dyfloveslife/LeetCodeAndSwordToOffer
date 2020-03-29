package LeetCodeSolution.AlgorithmThought._06_Search._90_SubsetsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 子集 Ⅱ
 *
 * 题目描述：
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 *
 * 思路：
 * 1. 同样是回溯，但需要注意应进行剪枝；
 * 2. 此外，数组中的元素可能存在重复的元素，因此需要进行排序，然后再去重。
 */
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        // 先排序
        Arrays.sort(nums);

        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(res, path, visited, nums, 0);
        return res;
    }

    public void dfs(List<List<Integer>> res, List<Integer> path, boolean[] visited, int[] nums, int start) {
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            // 剪枝
            if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == false) {
                continue;
            }

            path.add(nums[i]);
            visited[i] = true;
            dfs(res, path, visited, nums, i + 1);
            visited[i] = false;
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {1, 2, 2};
        System.out.println(s.subsetsWithDup(nums));
    }
}
