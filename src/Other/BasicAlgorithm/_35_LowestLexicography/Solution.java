package Other.BasicAlgorithm._35_LowestLexicography;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 字典序最小的字符串
 *
 * 题目描述：
 * 有一个包含许多字符串的数组，将每个字符串进行拼接，这肯定有许多不同种拼接的结果。
 * 找出其中字典序最小的拼接后的字符串。
 *
 * 思路：
 * 1. 使用贪心，但不能按照比较字符的大小进行，例如 b 和 ba，得到的结果是 bab < bba，其实答案是 bba < bab;
 * 2. 之前的方法是比较 str1 与 str2 的大小，谁小就将谁放在前面；
 * 3. 但是以上是不正确的，反例也给出了；
 * 4. 正确的比较方式是如果 str1.str2 ≤ str2.str1，则将 str1 放在前面；
 * 5. 这里涉及到比较策略的传递性。
 */
public class Solution {

    public static class MyComparator implements Comparator<String> {

        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static String lowestString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        Arrays.sort(strs, new MyComparator());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] strs1 = {"jibw", "ji", "jp", "bw", "jibw"};
        System.out.println(lowestString(strs1));

        String[] strs2 = {"ba", "b"};
        System.out.println(lowestString(strs2));
    }
}
