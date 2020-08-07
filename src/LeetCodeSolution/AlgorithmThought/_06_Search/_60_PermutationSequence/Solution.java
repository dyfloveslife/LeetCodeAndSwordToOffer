package LeetCodeSolution.AlgorithmThought._06_Search._60_PermutationSequence;

import java.util.ArrayList;
import java.util.List;

/*
 * 第 k 个排列
 *
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 说明：
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是 [1, n!]。
 *
 * 思路：
 * 1. 可以利用之前“全排列”题目的思想，只不过该题不用将所有的排列情况求出来，而是要进行剪枝；
 * 2. 如果当前层的排列数小于 k，则说明即使将当前层所有的排列都求出来，也达不到 k 的要求，因此就可以剪枝了。
 */
public class Solution {
    // 该题可以不用将全部的排列数都求出来，而是利用剪枝操作，减少计算量
    public String getPermutation(int n, int k) {
        if (n < 1) {
            return "";
        }
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i + 1;
        }
        boolean[] visited = new boolean[n];
        List<String> list = new ArrayList<>();
        return dfs(nums, list, visited, n, k, 0);
    }

    /**
     * @param nums    源数组，例如当 n 等于 4 的时候，num = [1, 2, 3, 4]
     * @param list    每一层的选择
     * @param visited 数组中的元素是否被访问过
     * @param n       一共有 n 个值
     * @param k       第 k 个
     * @param depth   深度，当前使用元素的索引
     * @return
     */
    private String dfs(int[] nums, List<String> list, boolean[] visited, int n, int k, int depth) {
        // 递归结束的条件，收集结果
        if (depth == n) {
            StringBuilder sb = new StringBuilder();
            for (String str : list) {
                sb.append(str);
            }
            return sb.toString();
        }
        // 计算还有多少排列数
        int cur = factorial(n - depth - 1);
        for (int i = 0; i < n; i++) {
            // 剪枝
            if (visited[i]) {
                continue;
            }
            // 当前的排列组合数小于 k，就说明这一层即使排完了也到不了第 k 个，因此剪枝
            if (cur < k) {
                k -= cur;
                continue;
            }
            list.add(String.valueOf(nums[i]));
            // 将元素添加到结果集中以后，要设置已被访问过
            visited[i] = true;
            return dfs(nums, list, visited, n, k, depth + 1);
        }
        return null;
    }

    // 计算 n 的阶乘
    private int factorial(int n) {
        int ans = 1;
        while (n != 0) {
            ans *= n;
            n -= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 4;
        int k = 9;

        System.out.println(solution.getPermutation(n, k));
    }
}
