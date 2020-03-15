package SwordToOfferSolution._20_NumericStrings;

/*
 * 表示数值的字符串
 *
 * 题目描述：
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串 “+100”、“5e2”、“-123”、“3.1416” 及 “-1E-16” 都表示数值，但 “12e”、“1a3.14”、“1.2.3”、“+-5” 及 “12e+5.4” 都不是。
 *
 * 思路：
 *	[]  ： 字符集合
 *	()  ： 分组
 *	?   ： 重复 0 ~ 1 次
 *	+   ： 重复 1 ~ n 次
 *	*   ： 重复 0 ~ n 次
 *	.   ： 任意字符
 *	\\. ： 转义后的 .
 *	\\d ： 数字
 */
public class Solution {
    public static boolean isNumeric(String s) {
        if (s == null || s.trim().length() == 0) {
            return false;
        }
        s = s.trim();
        int[] idx = {0};
        boolean flag = judgeNumber(s, idx);
        if (idx[0] != s.length() && s.charAt(idx[0]) == '.') {
            idx[0]++;
            //System.out.println(idx[0]);
            flag = judgeUnsignedNumber(s, idx) || flag;
        }
        if (idx[0] != s.length() && (s.charAt(idx[0]) == 'e' || s.charAt(idx[0]) == 'E')) {
            idx[0]++;
            flag = flag && judgeNumber(s, idx);
        }
        return flag && idx[0] == s.length();
    }

    private static boolean judgeNumber(String s, int[] idx) {
        if (idx[0] >= s.length()) {
            return false;
        }
        if (s.charAt(idx[0]) == '+' || s.charAt(idx[0]) == '-') {
            idx[0]++;
        }
        return judgeUnsignedNumber(s, idx);
    }

    private static boolean judgeUnsignedNumber(String s, int[] idx) {
        int before = idx[0];
        while (idx[0] < s.length() && s.charAt(idx[0]) <= '9' && s.charAt(idx[0]) >= '0') {
            idx[0]++;
        }
        //System.out.println(before + ":" + idx[0]);
        return idx[0] == before ? false : true;
    }

    public static void main(String[] args) {
        System.out.println(isNumeric("123.45e+6"));
        System.out.println(isNumeric("e9"));
        System.out.println(isNumeric("1 "));
    }
}
