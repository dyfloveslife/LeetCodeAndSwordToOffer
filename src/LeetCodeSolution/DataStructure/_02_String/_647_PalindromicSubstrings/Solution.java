package LeetCodeSolution.DataStructure._02_String._647_PalindromicSubstrings;

/*
 * 回文子串
 *
 * 题目描述：
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * 思路：
 * 1. 注意：求的是子串，需要连续；
 * 2. 如果使用暴力的方式，那么直接逐个判断即可，时间复杂度为 O(n^2)；
 * 3. 还可以使用 dp 的方法；
 * 4. dp[i][j] 表示字符串 s[i...j] 是否为回文子串，如果是，则 dp[i][j] = true，否则为 false；
 * 5. 例如 s =  b  a  b  a  d
 *             i  i+1   j-1 j
 * 6. 如果已经知道 dp[i+1][j-1] 了，那么如果 s[i]==s[j]，则 dp[i][j] = true，
 *                                     如果 s[i]!=s[j]，则 dp[i][j] = false；
 * 7. 此外，对于 s = c   b   b   d 的情况，
 *                      i   j
 *    中间已经没有 i+1 和 j-1 了，因此需要单独处理成 true。
 */
public class Solution {
    public int countSubstrings1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int res = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindromic(chars, i, j)) {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean isPalindromic(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public int countSubstrings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int res = s.length();
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i == 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j]) {
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "abc";
        String s2 = "aaa";

        System.out.println(solution.countSubstrings1(s1));
        System.out.println(solution.countSubstrings1(s2));
        System.out.println("--");

        System.out.println(solution.countSubstrings2(s1));
        System.out.println(solution.countSubstrings2(s2));
    }
}
