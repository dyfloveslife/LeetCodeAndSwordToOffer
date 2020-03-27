package LeetCodeSolution.AlgorithmThought._06_Search._77_Combinations;

import java.util.ArrayList;
import java.util.List;

/*
 * 组合
 *
 * 题目描述：
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 思路：
 * 1. 回溯法；
 * 2. 注意剪枝操作。
 */
public class Solution {
    public static List<List<Integer>> combine (int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < 1 || k < 1) {
            return  res;
        }

        List<Integer> path = new ArrayList<>();
        dfs(res, path, n, k, 1);
        return res;
    }


    public static void dfs(List<List<Integer>> res, List<Integer> path, int n, int k, int start) {
        // 来到了树的底部，则开始生成结果
        // 由于限制是 k 个数的组合
        if (k == path.size()) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 不断遍历 1 到 n 中的每个数
        // 剪枝：n - (k - path.size()) + 1
        for (int i = start; i <= n; i++) {
            path.add(i);
            // 注意是 i + 1
            dfs(res, path, n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(combine(4, 2));
    }
}
