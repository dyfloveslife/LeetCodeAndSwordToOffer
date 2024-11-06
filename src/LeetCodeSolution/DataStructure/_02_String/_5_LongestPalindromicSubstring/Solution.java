package LeetCodeSolution.DataStructure._02_String._5_LongestPalindromicSubstring;

/*
 * 最长回文子串
 *
 * 题目描述：
 * 给定一个字符串 s，找到 s 中最长的回文子串。
 *
 * 思路：Manacher 算法
 * 1、https://github.com/dyfloveslife/LeetCodeAndSwordToOffer/blob/master/src/Other/AdvancedAlgorithm/_04_Manacher/Solution.java
 * 2、扩展串中的回文半径和真实回文长度的对应，真实长度 = p[i] - 1；
 * 3、扩展串结尾下标和真实回文串终止位置的对应，真实回文串终止位置 = 扩展回文串结尾下标 / 2。
 */
public class Solution {

    /**
     * 方法一
     *
     * @param s String
     * @return String
     */
    public String longestPalindrome1(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }

        int maxLen = 1;
        int start = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                // 如果遇到了一个更长的回文串，则更新 maxLen，同时定位这个字符串的起始位置
                if (j - i + 1 > maxLen && isPalindrome(chars, i, j)) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }

        return s.substring(start, start + maxLen);
    }

    /**
     * 方法二
     *
     * @param s String
     * @return String
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }

        char[] chars = manacherString(s);
        int n = chars.length;
        int[] pArr = new int[n];
        // max 表示扩展串中的最大回文半径
        // end 表示在扩展串中取得最大回文半径的结束为止
        int max = 0, end = 0;
        for (int i = 0, c = 0, r = 0, len; i < n; i++) {
            len = r > i ? Math.min(pArr[2 * c - i], r - i) : 1;

            while (i + len < n && i - len >= 0 && chars[i + len] == chars[i - len]) {
                len++;
            }
            if (i + len > r) {
                r = i + len;
                c = i;
            }
            if (len > max) {
                max = len - 1;
                end = (i + len - 1) / 2;
            }
            pArr[i] = len;
        }

        return s.substring(end - max, end);
    }

    /**
     * 将字符 '#' 插入到字符串 s 中
     *
     * @param s String
     * @return char[]
     */
    private char[] manacherString(String s) {
        char[] chars = s.toCharArray();
        char[] res = new char[s.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : chars[index++];
        }

        return res;
    }

    private boolean isPalindrome(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "babad";
        String s2 = "cbbd";

        System.out.println(solution.longestPalindrome1(s1));
        System.out.println(solution.longestPalindrome1(s2));

        System.out.println("---");
        System.out.println(solution.longestPalindrome2(s1));
        System.out.println(solution.longestPalindrome2(s2));
    }
}
