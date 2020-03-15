package SwordToOfferSolution._58_01_ReverseWordsInSentence;

import javafx.beans.binding.StringBinding;

/*
 * 翻转单词顺序
 *
 * 题目描述：
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
 * 为简单起见，标点符号和普通字母一样处理。
 * 例如输入字符串 "I am a student. "，则输出 "student. a am I"。
 *
 * 思路：
 * 1. 不能使用额外的辅助空间；
 * 2. 先翻转整个句子，然后再翻转每个单词；
 * 3. 翻转单词的时候，根据空格来确定每个单词的起始位置;
 * 4. 也可以先翻转每个单词，再翻转句子。
 * 5. 用双指针，begin 和 end 分别控制单词的首尾，end 不断往后移动；
 * 6. 如果 end 到了最后或者遇到了空格，则翻转每个单词；
 * 7. 翻转完后要更新 begin 的位置，并且 end 继续向后移动；
 * 8. 直到最后一个单词翻转完后，再翻转整个句子。
 * 9. 需要注意的是：
 *      三个空格被 split(" ") 划分后会变成 ""；
 *      这里应使用 equals 而不是 == 号。
 */
public class Solution {
    public static String reverseWords(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        String[] strArr = str.split(" ");
        StringBuilder sb = new StringBuilder();
        // 从后往前遍历
        for (int i = strArr.length - 1; i >= 0; i--) {
            if (!strArr[i].equals("")) {
                sb.append(strArr[i]).append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static String reverseWordsInSentence(String str) {
        int len = str.length();
        if (str == null || len < 1) {
            return "";
        }

        char[] chars = str.toCharArray();
        int begin = 0;
        int end = 0;
        while (end <= len) {
            // 翻转每个单词，翻转完后更新 begin 的位置，然后 end 继续后移
            if (end == len || chars[end] == ' ') {
                reverse(chars, begin, end - 1);
                begin = end + 1;
            }
            end++;
        }
        // 翻转整个句子
        reverse(chars, 0, len - 1);
        return new String(chars);
    }

    private static void reverse(char[] chars, int i, int j) {
        while (i < j) {
            // 对单词中的每个字符进行交换，i 不断右移，j 不断左移
            swap(chars, i++, j--);
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        String str = "I am a student.";
        String str1 = "a good   example";
        System.out.println(reverseWordsInSentence(str1));
        System.out.println(reverseWords(str1));
    }
}