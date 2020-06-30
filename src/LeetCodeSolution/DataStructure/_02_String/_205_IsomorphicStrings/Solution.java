package LeetCodeSolution.DataStructure._02_String._205_IsomorphicStrings;

/*
 * 同构字符串
 *
 * 题目描述：
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。
 * 两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *
 * 思路：
 * 1. 记录当前字符上一次出现的位置，如果两个字符串中的字符上次出现的位置一样，那么就属于同构的。
 */
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }

        int[] nums1 = new int[128];
        int[] nums2 = new int[128];
        for (int i = 0; i < s.length(); i++) {
            char ss = s.charAt(i);
            char tt = t.charAt(i);
            // 如果两个字符上次出现的位置不一样，则直接返回 false
            if (nums1[ss] != nums2[tt]) {
                return false;
            }

            nums1[ss] = i + 1;
            nums2[tt] = i + 1;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "foo";
        String t1 = "bar";
        String s2 = "paper";
        String t2 = "title";

        System.out.println(solution.isIsomorphic(s1, t1));
        System.out.println(solution.isIsomorphic(s2, t2));
    }
}
