package SwordToOfferSolution._48_LongestSubstringWithoutDup;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * 最长不含重复字符的子字符串
 *
 * 题目描述：
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * 假设字符串中只包含从 a 到 z 的字符。
 *
 * 思路 1：DP
 * https://dyfloveslife.github.io/2019/12/11/offer-longest-SubString-Without-Duplication/
 *
 * 思路 2：滑动窗口
 * 1、使用两个指针（start和end）来定义一个滑动窗口。
 * 2、使用一个集合（Set）来存储当前窗口中的字符。
 * 3、移动end指针，不断扩大窗口，直到遇到重复字符。
 * 4、当遇到重复字符时，移动start指针，缩小窗口，直到重复字符被移除。
 * 5、在这个过程中，持续更新最长子串的长度。
 */
public class Solution {

    public int longestSubstringWithoutDup0(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int left = 0;
        int right = 0;
        int max = 0;
        HashSet<Character> set = new HashSet<>();

        while (right < s.length()) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
                max = Math.max(max, set.size());
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }
        return max;
    }

    public int longestSubstringWithoutDup1(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }

        int curLen = 0;
        int maxLen = 0;
        int[] position = new int[26];
        Arrays.fill(position, -1);
//        for (int i = 0; i < position.length; i++) {
//            position[i] = -1;
//        }

        for (int curIndex = 0; curIndex < str.length(); curIndex++) {
            int c = str.charAt(curIndex) - 'a';
            int preIndex = position[c];

            if (preIndex == -1 || curIndex - preIndex > curLen) {
                curLen++;
            } else {
                maxLen = Math.max(curLen, maxLen);
                curLen = curIndex - preIndex;
            }
            position[c] = curIndex;
        }
        maxLen = Math.max(curLen, maxLen);
        return maxLen;
    }

    public int longestSubstringWithoutDup2(String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int preLength = 0;
        int curLength = 0;
        int maxLength = 0;
        int[] position = new int[26];
        for (int i = 0; i < position.length; i++) {
            position[i] = -1;
        }
        for (int i = 0; i < str.length(); i++) {
            int prevIndex = position[str.charAt(i) - 'a'];
            // 第i个字符之前没有出现过或者出现过但是距离大于f(i-1)
            if (prevIndex < 0 || i - prevIndex > preLength) {
                curLength = preLength + 1;
            } else {
                curLength = i - prevIndex;
            }
            position[str.charAt(i) - 'a'] = i;
            if (curLength > maxLength) {
                maxLength = curLength;
            }
            preLength = curLength;
        }
        return maxLength;
    }

    /**
     * 滑动窗口
     *
     * @param s String
     * @return int
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int n = s.length();
        int start = 0, end = 0;
        int maxLen = 0;
        Set<Character> set = new HashSet<>();

        while (start < n && end < n) {
            char c = s.charAt(end);
            // 如果字符不在 set 中，说明不重复，则将其加入到 set 中，并且 end 继续向后移动
            if (!set.contains(c)) {
                set.add(c);
                end++;
                maxLen = Math.max(maxLen, end - start);
            } else {
                set.remove(s.charAt(start));
                start++;
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "arabcacfr";

        System.out.println(solution.lengthOfLongestSubstring(str));
        System.out.println(solution.longestSubstringWithoutDup0(str));
        System.out.println(solution.longestSubstringWithoutDup1(str));
        System.out.println(solution.longestSubstringWithoutDup2(str));
    }
}
