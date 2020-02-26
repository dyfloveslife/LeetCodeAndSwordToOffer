package Other.AdvancedAlgorithm._22_RegularExpressionMatch;

/*
 * 正则匹配问题
 *
 * 题目描述：
 * 给定一个字符串，其中绝对不含有 . 和 *，再给定字符串 exp，其中可以含有 . 或 *。
 * 字符 * 不能是 exp 的首字符，并且任意两个 * 字符不相邻。
 * exp 中的 . 代表任何一个字符，而 * 表示 * 的前一个字符可以有 0 个或者多个。
 * 实现一个函数，判断 str 是是否能被 exp 匹配。
 */
public class Solution {

    public static boolean match(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] exp = p.toCharArray();
        return process(str, exp, 0, 0);
    }

    // 暴力递归
    public static boolean process(char[] str, char[] exp, int i, int j) {
        if (j == exp.length) {
            return i == str.length;
        }

        // j 位置上还有元素，现在考察 j+1 位置上的元素
        // j+1 索引已经越界，或者 j+1 位置上面的元素不是 *
        if (j + 1 == exp.length || exp[j + 1] != '*') {
            return i != str.length && (str[i] == exp[j] || exp[j] == '.')
                    && process(str, exp, i + 1, j + 1);
        }

        // exp 的 j+1 位置不仅有字符，而且是 *
        while (i != str.length && (str[i] == exp[j] || exp[j] == '.')) {
            if (process(str, exp, i, j + 2)) {
                return true;
            }
            i++;
        }
        return process(str, exp, i, j + 2);
    }

    public static void main(String[] args) {
        String str1 = "mississippi";
        String exp1 = "mis*is*p*.";
        System.out.println(match(str1, exp1));

        String str2 = "aab";
        String exp2 = "c*a*b";
        System.out.println(match(str2, exp2));

        String str3 = "ab";
        String exp3 = ".*";
        System.out.println(match(str3, exp3));

        String str4 = "";
        String exp4 = ".*";
        System.out.println(match(str4, exp4));
    }
}
