package LeetCodeSolution.AlgorithmThought._06_Search._40_CombinationSumII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 组合总和Ⅱ
 *
 * 题目描述：
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 *
 * 思路：
 * 1. 由于给定的数组中有可能存在相同的数，所以需要额外的判断；
 * 2. 因此需要先对数组进行排序，然后在 dfs 内部进行去重操作。
 */
public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0 || target == 0) {
            return res;
        }

        // 先对数组排序
        Arrays.sort(candidates);

        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[candidates.length];

        dfs(res, path, candidates, visited, target, 0);

        return res;
    }

    public void dfs(List<List<Integer>> res, List<Integer> path, int[] candidates, boolean[] visited, int target, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 去重
            // 由于 candidates 已经是排好序的了，如果 [i] 和 [i-1] 相等，则说说明存在重复的元素
            // !visited[i - 1] 剪枝更彻底
            if (i > 0 && candidates[i] == candidates[i - 1] && visited[i - 1] == false) {
                continue;
            }

            if (target >= candidates[i]) {
                path.add(candidates[i]);
                visited[i] = true;
                dfs(res, path, candidates, visited, target - candidates[i], i + 1);
                visited[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        System.out.println(s.combinationSum2(arr, target));
    }
}
