package LeetCodeSolution.AlgorithmThought._01_DoublePointer._3_LongestSubstringWithoutRepeatingCharacters;

import java.util.HashSet;

/*
 * 无重复字符的最长子串
 *
 * 题目描述：
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 *
 * 思路：
 * 1. 使用 HashSet 和双指针；
 * 2. 需要注意的是，如果 set 中已经包含了 right 位置所指的字符，那么应该将 left 所指位置的字符从 set 中删除。
 * 3. 当然，其中的 HashSet 可以使用数组进行代替，因为给定的字符串都是字母。
 */
public class Solution {

    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] map = new int[128];
        int ans = 0;

        for (int i = 0, j = 0; i < s.length(); ++i) {
            map[s.charAt(i)]++;
            while (map[s.charAt(i)] > 1) {
                map[s.charAt(j)]--;
                j++;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        HashSet<Character> set = new HashSet<>();
        int max = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (!set.contains(c)) {
                set.add(c);
                right++;
                max = Math.max(max, set.size());
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.lengthOfLongestSubstring1("abcabcbb"));
        System.out.println(solution.lengthOfLongestSubstring1("bbbbb"));
        System.out.println(solution.lengthOfLongestSubstring1("pwwkew"));

        System.out.println("--");
        System.out.println(solution.lengthOfLongestSubstring2("abcabcbb"));
        System.out.println(solution.lengthOfLongestSubstring2("bbbbb"));
        System.out.println(solution.lengthOfLongestSubstring2("pwwkew"));
    }
}
