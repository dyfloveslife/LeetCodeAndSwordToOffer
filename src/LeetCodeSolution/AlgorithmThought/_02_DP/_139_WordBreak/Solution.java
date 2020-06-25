package LeetCodeSolution.AlgorithmThought._02_DP._139_WordBreak;

import java.util.Arrays;
import java.util.List;

/*
 * 单词拆分
 *
 * 题目描述：
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 思路：
 * 1. 使用 DP 的思想，dp[i] 表示在 s 中的前 i 个字母（从 0 到 i-1）能够与字典中的单词进行匹配成功，也就是能够进行拆分；
 * 2. 或者可以说 dp[i] 表示以 i-1 结尾的字符串是否可以被字典拆分；
 * 3. 那么转移方程就是 dp[j] = dp[i] + check(s[i+1, j]);
 * 4. 例如wordDict=["apple", "pen", "code"],s = "applepencode";
 *    那么 dp[8] = dp[5] + check("pen") 表示如果想要判断 'c' 位置的话，则需要判断前 5 位（不包括 5）是否能够被拆分，以及从第 5 位到第 8 位上的字母。
 */
public class Solution {
    public boolean wordBreak(String s, List<String> workDict) {
        if (s == null || s.length() == 0) {
            return false;
        }

        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && workDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "applepenapple";
        List<String> wordDict = Arrays.asList("apple", "pen");

        System.out.println(solution.wordBreak(s, wordDict));
    }
}
