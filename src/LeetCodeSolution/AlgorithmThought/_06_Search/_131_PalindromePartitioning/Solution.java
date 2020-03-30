package LeetCodeSolution.AlgorithmThought._06_Search._131_PalindromePartitioning;

import java.util.ArrayList;
import java.util.List;

/*
 * 分割回文串
 *
 * 题目描述：
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 *
 * 思路：
 * 1. 回溯；
 * 2. 注意剪枝。
 */
public class Solution {

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }

        List<String> path = new ArrayList<>();
        dfs(res, path, s, 0);
        return res;
    }

    public void dfs(List<List<String>> res, List<String> path, String s, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            // 剪枝
            if (!isPalindrome(s, start, i)) {
                continue;
            }

            path.add(s.substring(start, i + 1));
            dfs(res, path, s, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.partition("aab"));
    }
}
