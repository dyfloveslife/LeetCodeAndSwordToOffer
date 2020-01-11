package SwordToOfferSolution._67_StringToInt;

/*
 * 把字符串转换成整数
 *
 * 题目描述：
 * 请你写一个函数StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
 *
 * 思路：
 * 该题需要考虑的特殊情况比较多，例如：数据溢出、空串、只有正负号、有无正负号、非法输入等。
 */
public class Solution {
    public static int strToInt(String str) {
        if (str == null || str.trim().length() == 0) {
            return 0;
        }

        // 判断是否是负数
        boolean isNegative = str.charAt(0) == '-';
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            // 取每一个字符逐一判断
            char c = str.charAt(i);
            // 如果第一个字符含有正号或负号
            if (i == 0 && (c == '+' || c == '-')) {
                continue;
            }
            // 非法输入
            if (c < '0' || c > '9') {
                return 0;
            }
            // 将 字符数字 转化成 数字
            res = res * 10 + (c - '0');
        }
        return isNegative ? -res : res;
    }
}