package SwordToOfferSolution._58_02_LeftRotateString;

/*
 * 左旋转字符串
 *
 * 题目描述：
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
 * 请定义一个函数实现字符串左旋转操作的功能。
 * 比如输入字符串 "abcdefg" 和数字 2，该函数将返回左旋转 2 位得到的结果 "cdefgab"。
 *
 * 思路：
 * 1. 利用上一题的思路，将字符串分成两部分；
 * 2. 将前半部分和后半部分分别进行翻转；
 * 3. 然后将整个字符串进行翻转。
 */
public class Solution {

    public String reverseLeftWords(String s, int n) {
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(n, s.length())).append(s.substring(0, n));

        return sb.toString();
    }

    public String leftRotateString(String str, int n) {
        if (str == null || str.length() < 1) {
            return "";
        }

        int len = str.length();
        // 边界条件，如果输入的 n 大于 str 的长度，则直接输出原先的 str
        if (n > len) {
            return str;
        }

        char[] chars = str.toCharArray();
        reverse(chars, 0, n - 1);
        reverse(chars, n, len - 1);
        reverse(chars, 0, len - 1);
        return new String(chars);
    }

    private void reverse(char[] chars, int i, int j) {
        while (i < j) {
            swap(chars, i++, j--);
        }
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "abcdefg";

        System.out.println(solution.reverseLeftWords(str, 3));
        System.out.println(solution.leftRotateString(str, 2));
    }
}
