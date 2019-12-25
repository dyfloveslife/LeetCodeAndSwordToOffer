package SwordToOfferSolution._58_02_LeftRotateString;

/*
 * 左旋转字符串
 *
 * 思路：
 * 1. 利用上一题的思路，将字符串分成两部分；
 * 2. 将前半部分和后半部分分别进行翻转；
 * 3. 然后将整个字符串进行翻转。
 */
public class Solution {
    public static String leftRotateString(String str, int n) {
        if (str == null || str.length() < 1) {
            return "";
        }

        int len = str.length();
        if (n > len) {
            return str;
        }

        char[] chars = str.toCharArray();
        reverse(chars, 0, n - 1);
        reverse(chars, n, len - 1);
        reverse(chars, 0, len - 1);
        return new String(chars);
    }

    private static void reverse(char[] chars, int i, int j) {
        while (i < j) {
            swap(chars, i++, j--);
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        String str = "abcdefg";
        System.out.println(leftRotateString(str, 2));
    }
}
