package SwordToOfferSolution._05_ReplaceSpaces;

/*
 * 替换空格
 *
 * 题目描述：
 * 请实现一个函数，把字符串中的每个空格替换成 "%20"。
 * 例如输入 “We are happy.”，则输出 “We%20are%20happy.”。
 *
 * 思路：
 * 1. 由于 String 是不可变类型，所以这里使用 StringBuffer，即 sb；
 * 2. 将字符串转换成字符数组，判断当前的字符数组是否为空格字符：
 *    如果为空格字符，则在 sb 后添加一个字符串 %20；
 *    如果不是空格字符，则在 sb 后添加当前字符；
 *    最后将 StringBuffer 转化为 String  即可。
 */
public class Solution {
    public String replaceSpaces1(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private String replaceSpaces2(String str) {
        if (str.equals("")) {
            return str;
        }

        char[] strArray = str.toCharArray();
        int i = 0;
        int lengthSpaces = 0;
        while (i < strArray.length) {
            if (strArray[i] == ' ') {
                lengthSpaces++;
            }
            i++;
        }

        int newStrLength = strArray.length + lengthSpaces * 2;
        char[] newStr = new char[newStrLength];
        int indexOfOriginal = strArray.length - 1;
        int indexOfNew = newStrLength - 1;
        while (indexOfOriginal >= 0) {
            if (strArray[indexOfOriginal] != ' ') {
                newStr[indexOfNew--] = strArray[indexOfOriginal--];
            } else {
                newStr[indexOfNew--] = '0';
                newStr[indexOfNew--] = '2';
                newStr[indexOfNew--] = '%';
                indexOfOriginal--;
            }
        }
        return new String(newStr);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.replaceSpaces1("    "));
        System.out.println(solution.replaceSpaces1("We are happy."));
        System.out.println(solution.replaceSpaces2("    "));
        System.out.println(solution.replaceSpaces2("We are happy."));
    }
}