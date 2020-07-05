package LeetCodeSolution.DataStructure._02_String._44_WildcardMatching;

/*
 * 通配符匹配
 *
 * 题目描述：
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 *
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 *
 * 思路：
 * 1. dp[i][j] 表示 s 的前 i 个字符与 p 的前 j 个字符是否匹配；
 * 2. 如果 s[i-1]==p[j-1] 或 p[j-1]='?'，则说明当前的字符串是匹配的，那么 dp[i][j] 可以从 dp[i-1][j-1] 转移而来；
 * 3. 如果 p[j-1]='*'，则这个位置可以匹配 0 到多个字符，那么 dp[i][j] 可以从 dp[i-1][j] 或 dp[i][j-1] 转移而来；
 *    其中，dp[i-1][j] 表示当前 * 没有匹配字符，而 dp[i][j-1] 表示当前 * 匹配了当前位置的字符；
 *    这两种情况用或表示；
 * 4. 综上，dp[i][j] = dp[i-1][j-1], s[i-1]==p[j-1] || p[j-1]=='?'
 *                   = dp[i-1][j] | dp[i][j-1], p[j-1]=='*'
 */
public class Solution {
    public boolean isMatch(String s, String p) {
        // 字符串补一个空格是为了让字符的下标从 1 开始，便于处理
        s = " " + s;
        p = " " + p;
        int len1 = p.length();
        int len2 = s.length();

        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        // 空串是可以匹配的
        dp[0][0] = true;
        // 如果匹配串 p 以若干个星号开头，则星号可以匹配空串，因此需要特殊处理
//        for (int i = 1; i <= len1; i++) {
//            if (p.charAt(i - 1) != '*') {
//                break;
//            }
//            dp[i][0] = true;
//        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] | dp[i][j - 1];
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "aa";
        String p1 = "a";

        String s2 = "acdcb";
        String p2 = "a*c?b";

        System.out.println(solution.isMatch(s1, p1));
        System.out.println(solution.isMatch(s2, p2));
    }
}
