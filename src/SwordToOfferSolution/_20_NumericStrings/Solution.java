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
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }

        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
    }

    public static void main(String[] args) {
        Solution numericStrings = new Solution();
        boolean b = numericStrings.isNumeric("123.45e+6".toCharArray());
        System.out.println(b);
    }
}
