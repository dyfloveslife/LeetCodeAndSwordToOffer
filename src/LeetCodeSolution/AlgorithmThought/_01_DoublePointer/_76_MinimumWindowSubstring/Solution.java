package LeetCodeSolution.AlgorithmThought._01_DoublePointer._76_MinimumWindowSubstring;

import java.util.HashMap;

/*
 * 最小覆盖子串
 *
 * 题目描述：
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串
 *
 * 思路：
 * 1. 使用双指针控制滑动窗口的左右边界，即 left 和 right；
 * 2. 开始的时候 right 向右增大窗口，直到窗口中包含了所有的 T 字符；
 * 3. 根据 left 和 right 记录此时滑动窗口的长度，然后 left 向右移动，开始减少长度，每减少一次就更新最小长度；
 * 4. 直到当前窗口不包含所有字母，然后再回到第 2 步；
 * 5. 那么如何判断当前的窗口是否包含了所有 T 的字符？
 * 6. 使用 HashMap 统计 T 中字符所出现的次数，然后遍历 S，遇到相应的字母的时候就将对应的字母数量减 1；
 * 7. 如果此时 map 中所有字母的次数都小于等于 0，则说明当前窗口中一定包含了 T 中的所有字母；
 * 8. 注意：其中 minRight = -1 可以解决 s = "a"、t = "aa" 的情况。
 */
public class Solution {
    // 未优化
    public String minString(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        // 滑动窗口的左右边界
        int left = 0;
        int right = 0;
        // 用于保存最小滑动窗口的左右边界
        int minLeft = 0;
        int minRight = -1;
        // 当前最小窗口的长度
        int minLen = Integer.MAX_VALUE;

        while (right < s.length()) {
            char charRight = s.charAt(right);
            if (map.containsKey(charRight)) {
                map.put(charRight, map.get(charRight) - 1);
                // 如果当前 map 中的所有 value 都小于等于 0，
                // 则说明当前窗口中已经包含了 T 中的所有字母，
                // 那么我就可以开始收集当前窗口的大小了
                while (match(map)) {
                    int curLen = right - left + 1;
                    // 如果当前窗口更小，则更新窗口的左右边界以及当前窗口的最小长度
                    if (curLen < minLen) {
                        minLeft = left;
                        minRight = right;
                        minLen = curLen;
                    }
                    // 如果 map 中含有 left 所指的字母，则需要将该字母移除，同时次数要加 1
                    char charLeft = s.charAt(left);
                    if (map.containsKey(charLeft)) {
                        map.put(charLeft, map.get(charLeft) + 1);
                    }
                    left++;
                }
            }
            right++;
        }
        return s.substring(minLeft, minRight + 1);
    }

    // 判断所有 value 是否大于 0
    private boolean match(HashMap<Character, Integer> map) {
        for (Integer value : map.values()) {
            if (value > 0) {
                return false;
            }
        }
        return true;
    }

    // 优化后
    // 由于题目给定的都是字母，因此可以使用数组来统计每个字母出现的次数，
    public String minString2(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }

        int[] map = new int[128];
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i)]++;
        }

        int left = 0;
        int right = 0;
        int minLeft = 0;
        int minRight = -1;
        int minLen = Integer.MAX_VALUE;
        int count = t.length();

        while (right < s.length()) {
            char charRight = s.charAt(right);
            map[charRight]--;
            if (map[charRight] >= 0) {
                count--;
            }

            // 当前窗口中已经收集到了 T 中所有的字母
            while (count == 0) {
                int curLen = right - left + 1;
                if (curLen < minLen) {
                    minLeft = left;
                    minRight = right;
                    minLen = curLen;
                }
                char charLeft = s.charAt(left);
                // 因为要把当前字母移除，所以相应次数要加 1
                map[charLeft]++;
                // 大于 0 说明缺少当前字母，因此 count 需要加 1
                if (map[charLeft] > 0) {
                    count++;
                }
                left++;
            }
            right++;
        }

        return s.substring(minLeft, minRight + 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.println(solution.minString(s, t));
        System.out.println(solution.minString2(s, t));
    }
}
