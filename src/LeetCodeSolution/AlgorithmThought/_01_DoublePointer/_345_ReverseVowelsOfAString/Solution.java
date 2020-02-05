package LeetCodeSolution.AlgorithmThought._01_DoublePointer._345_ReverseVowelsOfAString;

import java.util.Arrays;
import java.util.HashSet;

/*
 * 反转字符串中的元音字母
 *
 * 题目描述：
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *
 * 思路：
 * 1. 为了取值方便，可以先将所有的大小写元音字母放到 set 中；
 * 2. 采用双指针的方式，左右指针向中间遍历，如果左右指针都遇到了元音字母，则交换它们；
 * 3. 整体流程就是：获取到 s 当前的字符，看它在没在 set 中，如果在 set 中，则交换元音字母，
 *                 如果没在 set 中，则将当前位置的字符放进一个准备好的字符数组中，然后来到下一个位置；
 *                 最后再将填充好的字符数组转换成字符串返回即可。
 * 4. 当一头遍历到元音字母而另一头没遍历到时，则移动没遍历到元音字母的那端。
 */
public class Solution {

    private static final HashSet<Character> vowels = new HashSet<>(
            Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    public static String reverseVowels(String s) {
        if (s == null) {
            return null;
        }

        int left = 0;
        int right = s.length() - 1;
        char[] res = new char[s.length()];

        while (left <= right) {
            char c1 = s.charAt(left);
            char c2 = s.charAt(right);
            if (!vowels.contains(c1)) {
                res[left++] = c1;
            } else if (!vowels.contains(c2)) {
                res[right--] = c2;
            } else {
                res[left++] = c2;
                res[right--] = c1;
            }
        }
//        return String.valueOf(res);
        return new String(res);
    }

    public static void main(String[] args) {
        System.out.println(Solution.reverseVowels("leetcode"));
        System.out.println(Solution.reverseVowels("hello"));
    }
}
