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
 *
 * 1. 以前的方法有些繁琐，并且也不是容易理解，下面换一种方法；
 * 2. 将给定的字符串转换成字符数组，分别判断每个字符是否符合要求即可。
 */
public class Solution {
    public boolean isNumeric2(String s) {
        if (s == null || s.trim().length() == 0) {
            return false;
        }

        s = s.trim();
        // 是否遇到过数字、点、符号 e
        boolean numSeen = false;
        boolean dotSeen = false;
        boolean eSeen = false;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                numSeen = true;
            } else if (chars[i] == '.') {
                // . 之前不能出现 . 或 e
                if (dotSeen || eSeen) {
                    return false;
                }
                dotSeen = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                // e 之前不能出现 e，必须出现数字
                if (eSeen || !numSeen) {
                    return false;
                }
                eSeen = true;
                // 重置 numSeen，确保 e 之后也能出现数字
                numSeen = false;
            } else if (chars[i] == '+' || chars[i] == '-') {
                // 加号或者减号，只有出现在 0 位置，或者 e 后面的第一个位置，此时才是合法的
                if (i != 0 && chars[i - 1] != 'e' && chars[i - 1] != 'E') {
                    return false;
                }
            } else {
                // 其它不合法字符
                return false;
            }
        }
        return numSeen;
    }

    public boolean isNumeric1(String s) {
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

    private boolean judgeNumber(String s, int[] idx) {
        if (idx[0] >= s.length()) {
            return false;
        }
        if (s.charAt(idx[0]) == '+' || s.charAt(idx[0]) == '-') {
            idx[0]++;
        }
        return judgeUnsignedNumber(s, idx);
    }

    private boolean judgeUnsignedNumber(String s, int[] idx) {
        int before = idx[0];
        while (idx[0] < s.length() && s.charAt(idx[0]) <= '9' && s.charAt(idx[0]) >= '0') {
            idx[0]++;
        }
        //System.out.println(before + ":" + idx[0]);
        return idx[0] == before ? false : true;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.isNumeric1("123.45e+6"));
        System.out.println(solution.isNumeric1("e9"));
        System.out.println(solution.isNumeric1("1 "));
        System.out.println(solution.isNumeric1("+100"));
        System.out.println(solution.isNumeric1(". 1"));
        System.out.println("===");
        System.out.println(solution.isNumeric1("123.45e+6"));
        System.out.println(solution.isNumeric1("e9"));
        System.out.println(solution.isNumeric1("1 "));
        System.out.println(solution.isNumeric2("+100"));
        System.out.println(solution.isNumeric1(". 1"));
    }
}
