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
        // j 已经到了 exp 的最后一个位置的下一个位置了，
        // 已经不能匹配任何东西了，如果此时 i 也是到达了最后一个位置的下一个位置，
        // 则返回 true。
        // 也就是说，当 exp 耗尽的时候，str 必须也得耗尽，才能返回 true
        if (j == exp.length) {
            return i == str.length;
        }

        // j 位置上还有元素，现在考察 j+1 位置上的元素
        // j+1 索引已经越界，或者 j+1 位置上面的元素不是 *，
        // 在此情况下，如果 i 没有来到末尾，并且 i 和 j 位置对应的字符相等或 j 位置上的字符是点，
        // 此时还不足以返回 true，还应该递归比较后序的位置
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

    // DP
    public boolean isMatchDP(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();

        boolean[][] dp = initDPMap(s, e);
        for (int i = s.length - 1; i > -1; i--) {
            for (int j = e.length - 2; j > -1; j--) {
                if (e[j + 1] != '*') {
                    dp[i][j] = (s[i] == e[j] || e[j] == '.') && dp[i + 1][j + 1];
                } else {
                    int si = i;
                    while (si != s.length && (s[si] == e[j] || e[j] == '.')) {
                        if (dp[si][j + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        si++;
                    }
                    if (dp[i][j] != true) {
                        dp[i][j] = dp[si][j + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }

    private boolean[][] initDPMap(char[] s, char[] e) {
        int slen = s.length;
        int elen = e.length;
        boolean[][] dp = new boolean[slen + 1][elen + 1];
        dp[slen][elen] = true;
        for (int j = elen - 2; j > -1; j = j - 2) {
            if (e[j] != '*' && e[j + 1] == '*') {
                dp[slen][j] = true;
            } else {
                break;
            }
        }
        if (slen > 0 && elen > 0) {
            if ((e[elen - 1] == '.' || s[slen - 1] == e[elen - 1])) {
                dp[slen - 1][elen - 1] = true;
            }
        }
        return dp;
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
