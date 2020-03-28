package LeetCodeSolution.AlgorithmThought._06_Search._39_CombinationSum;

import java.util.ArrayList;
import java.util.List;

/*
 * 组合求和
 *
 * 题目描述：
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 *
 * 思路：
 * 1. 回溯+剪枝；
 * 2. 注意进入下一层的条件，要求 剩余的值 target 要大于或等于当前值 candidates[i] 才可以；
 */
public class Solution {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0 || target < 0) {
            return res;
        }

        List<Integer> path = new ArrayList<>();
        dfs(res, path, candidates, target, 0);
        return res;
    }

    public static void dfs(List<List<Integer>> res, List<Integer> path, int[] candidates, int target, int start) {
        // 来到末尾的时候，假如目标值 target 为 0，说明找到了一种结果
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 剪枝条件：剩余值 target 要比当前值 candidates[i] 大或相等才可以
            if (candidates[i] <= target) {
                path.add(candidates[i]);
                dfs(res, path, candidates, target - candidates[i], i);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum(candidates, target));
        System.out.println(combinationSum(new int[]{8, 7, 4, 3}, 11));
    }
}
