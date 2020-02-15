package LeetCodeSolution.AlgorithmThought._03_Greedy._392_IsSubsequence;

/*
 * 判断是否是子序列
 *
 * 题目说明：
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 */
public class Solution {

    // 贪婪
    public static boolean isSubsequence1(String s, String t) {
        if (s == null || t == null) {
            return false;
        }

        char[] chars = s.toCharArray();
        int index = -1;
        for (char c : chars) {
            index = t.indexOf(c, index + 1);
            if (index == -1) {
                return false;
            }
        }
        return true;
    }

    // 双指针
    public static boolean isSubsequence2(String s, String t) {
        if (s == null || t == null) {
            return false;
        }

        int i = 0;
        int j = 0;
        while (i < t.length() && j < s.length()) {
            if (t.charAt(i) == s.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == s.length();
    }

    public static void main(String[] args) {
        String s = "abc";
        String s1 = "axc";
        String t = "ahbgdc";
        System.out.println(isSubsequence1(s, t));
    }
}
