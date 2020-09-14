package LeetCodeSolution.AlgorithmThought._02_DP._1143_LongestCommonSubsequence;

/*
 * 最长公共子序列
 *
 * 题目描述：
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 *
 * 思路：
 * 1. DP；
 * 2. 定义 dp[i][j] 表示 s1 中 1~i 字符与 s2 中 1~j 字符的最长公共子序列的长度；
 * 3. 之所以从 1 开始，是因为需要将 0 行和 0 列置为 0；
 *    例如 dp[0][3] 表示对于字符串 "" 和 "bab"，它们之间的最长公共子序列为 0；
 * 3. 如果 s1 中的 i 和 s2 中的 j 相等，则 dp[i][j] 就等于前一个字符的最长公共子序列再加上当前相等的这个 1，即 dp[i-1][j-1]+1；
 * 4. 如果 s1 中的 i 和 s2 中的 j 不相等，则就需要求出 i 之前和 j 之前的最大值，即 max(dp[i-1][j], dp[i][j-1])；
 */
public class Solution {

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        int len1 = text1.length();
        int len2 = text2.length();
        
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "abcde";
        String s2 = "ace";

        System.out.println(solution.longestCommonSubsequence(s1, s2));
    }
}
