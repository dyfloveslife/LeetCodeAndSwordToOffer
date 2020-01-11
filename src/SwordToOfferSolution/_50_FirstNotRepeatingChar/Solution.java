package SwordToOfferSolution._50_FirstNotRepeatingChar;

/*
 * 在字符串中，第一个只出现一次的字符位置
 *
 * 题目描述：
 * 在字符串中找出第一个只出现一次的字符。如输入 abaccdeff，则输出 b。
 *
 * 思路：
 * 使用每个字符的 ASCII 值最为数组的索引。
 */
public class Solution {
    public static int firstNotRepeatingChar(String str) {
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
        String str = "aabccde";
        System.out.println(firstNotRepeatingChar(str));
    }
}
