package Other.AdvancedAlgorithm._27_LongestSubstring;

/*
 * 最长公共子串
 *
 * 思路：
 * 1. https://www.nowcoder.com/questionTerminal/02e7cc263f8a49e8b1e1dc9c116f7602；
 * 2. https://www.cnblogs.com/dartagnan/archive/2011/10/06/2199764.html
 */
public class Solution {

    public int findLongest(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }

        int len1 = s1.length();
        int len2 = s2.length();
        int[][] dp = new int[len1][len2];
        int ans = 0;
        for (int i = 0; i < len1; ++i) {
            for (int j = 0; j < len2; ++j) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "1AB2345CD";
        String s2 = "12345EF";

        System.out.println(solution.findLongest(s1, s2));
    }
}
