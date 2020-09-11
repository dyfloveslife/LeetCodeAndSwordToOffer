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
 *    def backtrack(路径, 选择列表):
 *        if  满足结束条件:
 *            result.add(路径)
 *            return
 *
 *        for 选择 in 选择列表:
 *            做选择
 *            backtrack(路径, 选择列表)
 *            撤销选择
 * 3. 核心在于：主需要在递归之前做选择，而在递归之后撤销选择；
 * 4. 对于 abcd 来说，首先可以选择 abcd，然后回溯为 abc_，由于 d 已经被选择过了，以此再次回溯成 ab_ _，
 *    此时可以进一步选择为 abdc。这就是回溯的过程。
 */
public class Solution {

    // 也可以使用一个 boolean 数组保存是否已经访问过当前节点
    public static List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs1(res, path, visited, nums);
        return res;
    }

    private static void dfs1(List<List<Integer>> res, List<Integer> path, boolean[] visited, int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            path.add(nums[i]);
            dfs1(res, path, visited, nums);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }


    // 普通的
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        List<Integer> path = new ArrayList<>();
        dfs(res, path, nums);
        return res;
    }

    private static void dfs(List<List<Integer>> res, List<Integer> path, int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (path.contains(nums[i])) {
                continue;
            }
            // 做选择
            path.add(nums[i]);
            // 进入下一层决策树
            dfs(res, path, nums);
            // 撤销选择
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(permute1(nums));
    }
}
