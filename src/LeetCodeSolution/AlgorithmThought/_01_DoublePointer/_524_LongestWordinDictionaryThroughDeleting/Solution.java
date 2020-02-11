package LeetCodeSolution.AlgorithmThought._01_DoublePointer._524_LongestWordinDictionaryThroughDeleting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 通过删除字母匹配到字典里最长单词
 *
 * 题目描述：
 * 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。
 * 如果答案不止一个，返回长度最长且字典顺序最小的字符串。
 * 如果答案不存在，则返回空字符串。
 *
 * 说明：
 * 所有输入的字符串只包含小写字母；
 * 字典的大小不会超过 1000；
 * 所有输入的字符串长度不会超过 1000。
 *
 * 思路：
 * 1. 注意子串和子序列的区别；
 * 2. 首先提供一个求字符串的子序列的方法，该方法可以使用双指针的方式实现；
 * 3. 对于 l1 > l2，即对于已经得到的 res，如果 res 比 target 要长的话，则返回长度最长的，这也是符合题目要求的；
 * 4. 对于 l1 == l2 && res.compareTo(target) < 0，如果已经得到的 res 和 target 等长，则还需要判断谁在字典序中最小；
 * 5. 在 3) 和 4) 中只要有一个为真，则就没有再进行判断是否是子序列的必要了。
 */
public class Solution {
    public static String findLongestWord(String s, List<String> d) {
        String res = "";
        for (String target : d) {
            int l1 = res.length();
            int l2 = target.length();

            // 要求: 长度最长且在字典序中最小的字符串
            if (l1 > l2 || (l1 == l2 && res.compareTo(target) < 0)) {
                continue;
            }
            if (isSequence(s, target)) {
                res = target;
            }
        }
        return res;
    }

    // 判断 target 是不是 s 的子序列
    public static boolean isSequence(String s, String target) {
        int i = 0;
        int j = 0;

        // 总体的思想和分配饼干那题类似，即如果当前相等，则两个指针同时往后移动，
        // 如果不相等，则 target 往后移动，再进行判断是否相等
        while (i < s.length() && j < target.length()) {
            // 如果字符相等，则需要将 target 中的索引后移
            if (s.charAt(i) == target.charAt(j)) {
                j++;
            }
            // 如果字符不相等，则需要将 s 中的索引后移
            // 因为还需要判断 i+1 位置上的字符是否与当前 j 位置字符相等
            i++;
        }
        return j == target.length();
    }

    public static void main(String[] args) {
        String s1 = "abpcplea";
        List<String> list1 = new ArrayList<>(Arrays.asList("ale","apple","monkey","plea"));
        System.out.println(findLongestWord(s1, list1));

        List<String> list2 = new ArrayList<>(Arrays.asList("a","b","c"));
        System.out.println(findLongestWord(s1, list2));
    }
}
