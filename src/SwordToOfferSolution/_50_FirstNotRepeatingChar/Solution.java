package SwordToOfferSolution._50_FirstNotRepeatingChar;

/*
 * 在字符串中，第一个只出现一次的字符位置
 *
 * 题目描述：
 * 在字符串中找出第一个只出现一次的字符。如输入 abaccdeff，则输出 b。
 *
 * 思路：
 * 1. 使用每个字符的 ASCII 值做为数组的索引。
 * 2. 当然也可以使用当前字符减去字符 'a'，得到数值，然后用这个数值作为数组的索引，
 *    索引所对应的值就是该索引所出现的次数，
 */
public class Solution {

    public static char firstUniqChar1(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }

        int[] arr = new int[128];
        // 统计每个字符出现的次数
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }
        for (char c : s.toCharArray()) {
            if (arr[c - 'a'] == 1) {
                return c;
            }
        }
        return ' ';
    }


    public static int firstUniqChar2(String str) {
        if (str == null || str.length() < 1) {
            return -1;
        }

        int[] arr = new int[256];
        // 第一次遍历，用于统计每个字符出现的次数
        for (int i = 0; i < str.length(); i++) {
            arr[str.charAt(i)]++;
        }
        // 第二次遍历，用于找到出现次数为 1 的字符，并返回其索引
        for (int j = 0; j < str.length(); j++) {
            if (arr[str.charAt(j)] == 1) {
                return j;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar1("aabccde"));
        System.out.println(firstUniqChar2("aabccde"));
        System.out.println(firstUniqChar2(""));
    }
}