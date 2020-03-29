package LeetCodeSolution.AlgorithmThought._06_Search._216_CombinationSumIII;

import java.util.ArrayList;
import java.util.List;

/*
 * 组合求和 Ⅲ
 *
 * 题目描述：
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1~9 的正整数，并且每种组合中不存在重复的数字。
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 *
 * 思路：
 * 1. 回溯 + 递归；
 * 2. “相加之和”说明下一次需要寻找 target - i，i 为本层数值；
 * 3. “不存在重复的数字”说明 dfs 方法中存在参数 start；
 * 4. “解集不能包含重复的组合”说明输入参数要预先排序，由于题目本身就是从 1 到 9，已经排好序了。
 */
public class Solution {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (k == 0 || n == 0) {
            return res;
        }

        List<Integer> path = new ArrayList<>();
        dfs(res, path, n, k, 1);
        return res;
    }

    // residue: 剩余多少
    // start: 下一轮搜索的起始元素是多少
    public void dfs(List<List<Integer>> res, List<Integer> path, int residue, int k, int start) {
        if (k == 0 && residue == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (k == 0 || residue == 0) {
            return;
        }

        for (int i = start; i <= 9; i++) {
            path.add(i);
            dfs(res, path, residue - i, k - 1, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.combinationSum3(3, 9));
    }
}
