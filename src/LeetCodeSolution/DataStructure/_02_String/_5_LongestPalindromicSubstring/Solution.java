package LeetCodeSolution.DataStructure._02_String._5_LongestPalindromicSubstring;

/*
 * 最长回文子串
 *
 * 题目描述：
 * 给定一个字符串 s，找到 s 中最长的回文子串。
 *
 * 思路：
 * https://github.com/dyfloveslife/LeetCodeAndSwordToOffer/blob/master/src/Other/AdvancedAlgorithm/_04_Manacher/Solution.java
 */
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null) {
            return null;
        }
        if (s.length() == 0) {
            return "";
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
        String s = "babad";

        System.out.println(solution.longestPalindrome(s));
    }
}
