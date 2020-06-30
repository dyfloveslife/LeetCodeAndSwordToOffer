package LeetCodeSolution.DataStructure._02_String._242_ValidAnagram;

import java.util.HashMap;

/*
 * 有效的字母异位词
 *
 * 题目描述：
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 思路：
 * 1. 使用 HashMap 的 key 作为当前字母，value 作为当前字母出现的次数；
 * 2. 遍历 t 字符串，只要 t 中的字母不在 HashMap 中，则直接返回 false；
 * 3. 如果 t 中的字母在 HashMap 中，那么就将其数量减一；
 * 4. 如果减一后，当前字母出现的次数为 0，则将其从 HashMap 中移除。
 * 5. 还可以使用其它的方法，由于只含有字母，所以可以开一个长度为 26 的数组，索引为当前字母，值为当前字母出现的次数。
 */
public class Solution {
    public boolean isAnagram1(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() == 0 || t.length() == 0) {
            return true;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        char[] char1 = s.toCharArray();
        char[] char2 = t.toCharArray();
        if (char1.length != char2.length) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            map.put(char1[i], map.getOrDefault(char1[i], 0) + 1);
        }

        for (int j = 0; j < t.length(); j++) {
            if (!map.containsKey(char2[j])) {
                return false;
            } else {
                map.put(char2[j], map.get(char2[j]) - 1);
                if (map.get(char2[j]) == 0) {
                    map.remove(char2[j]);
                }
            }
        }
        return true;
    }

    public boolean isAnagram2(String s, String t) {
        int[] nums = new int[26];
        for (char c : s.toCharArray()) {
            nums[c - 'a']++;
        }
        for (char cc : t.toCharArray()) {
            nums[cc - 'a']--;
        }
        for (int num : nums) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "ab";
        String t1 = "a";
        String s2 = "anagram";
        String t2 = "nagaram";

        System.out.println(solution.isAnagram1(s1, t1));
        System.out.println(solution.isAnagram1(s2, t2));
        System.out.println("---");

        System.out.println(solution.isAnagram2(s1, t1));
        System.out.println(solution.isAnagram2(s2, t2));
    }
}
