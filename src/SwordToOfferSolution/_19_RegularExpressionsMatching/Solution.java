package SwordToOfferSolution._19_RegularExpressionsMatching;

/*
 * 正则表达式匹配
 *
 * 题目描述：
 * 请实现一个函数用来匹配包含 . 和 * 的正则表达式。模式中的字符 . 表示任意一个字符，而 * 表示它前面的字符可以出现任意次（含 0 次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串 aaa 与模式 a.a 和 ab*ac*a 匹配，但与 aa.a 及 ab*a 均不匹配。
 *
 * 思路：
 * 1. 想同题目，见 https://github.com/dyfloveslife/LeetCodeAndSwordToOffer/blob/master/src/Other/AdvancedAlgorithm/_22_RegularExpressionMatch/Solution.java
 * 2. 思路详情：https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof/solution/zhu-xing-xiang-xi-jiang-jie-you-qian-ru-shen-by-je/
 * 3. 定义主串为 A，模式串为 B，开始的时候从两个串的最后一位开始比较；
 * 4. 假设 A 的长度为 n，B 的长度为 m，现在需要关注的是 B 的最后一个字符的情况：有 3 种可能：正常字符、* 以及点号；
 *    4.1 如果 B 的最后一个字符是正常字符，那么就看 A[n-1] 和 B[m-1] 是否相等，相等则再看 A[n-2] 和 B[m-2] 是否相等，直到比较到 0 位置，
 *        如果不相等，则不能匹配，这就是子问题；
 *    4.2 如果 B 的最后一个字符是点号，则它能匹配任意字符，因此直接看 A[n-2] 和 B[m-2] 是否相等；
 *    4.3 如果 B 的最后一个字符是 *，则说明 B[m-2]=c 可以重复 0 次或多次，它们是一个整体，即 c*；
 *            如果 A[n-1] 是 0 个 c，那么 B 最后两个字符就废掉了，接下来能够匹配成功，则取决于 A[n-1] 和 B[m-3]；
 *            如果 A[n-1] 是多个 c 中的最后一个（例如 A[n-1]=c 或 A[n-1]='.'），A 匹配完往前挪一个，B 继续匹配，因为可以匹配多个，继续看 A[n-2] 和 B[m-1] 是否匹配。
 * 5. 转移方程：f[i][j] 表示 A 的前 i 个字符和 B 的前 j 个字符匹配；
 * 6. 对于情况 4.1 和 4.2，可以合并成一种：f[i][j] = f[i - 1][j - 1]；
 * 7. 对于情况 4.3，对于 c* 分为“看”和“不看”两种情况：
 *    “不看”：则直接砍掉模式串的后面两个字符，f[i][j] = f[i][j - 2]
 *    “看”：则模式串不动，主串往前移动一个字符，f[i][j] = f[i - 1][j]
 * 8. 特殊情况：
 *    空的主串和空的模式串是匹配的，因此 f[0][0] = true；
 *    空的主串和非空模式串，不能直接定义 true 或 false，必须进行计算，例如 A=""，B=a*b*c*；
 *    非空主串和空的模式串是不匹配的，即 f[1][0]=...=f[n][0]=false；
 *    非空主串和非空模式串，进行正常的计算。
 */
public class Solution {
    public boolean isMatch(String A, String B) {
        int n = A.length();
        int m = B.length();
        // 开 n+1 大小的数组，方便对空串进行处理
        boolean[][] f = new boolean[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                // 分为空的模式窜和非空的模式窜两种
                //j == 0 表示空的模式串
                if (j == 0) {
                    // 下面的这个逻辑就表示思路 8 中的 f[1][0]=...=f[n][0]=false
//                    if (i == 0) f[i][j] = true;
//                    else f[i][j] = false;
                    // 以上注释的两句，等同于下面这句
                    f[i][j] = (i == 0);
                    // 以下表示非空的模式串
                } else {
                    // 非空模式串分为两种情况：* 和 非 *
                    if (B.charAt(j - 1) != '*') {
                        // 对应思路 4.1 和 4.2 两种情况，现在将其合二为一
                        if (i > 0 && (A.charAt(i - 1) == B.charAt(j - 1) || B.charAt(j - 1) == '.')) {
                            f[i][j] = f[i - 1][j - 1];
                        }
                        // 碰到了 *，则分为“看”和“不看”两种情况
                    } else {
                        // 不看
                        if (j >= 2) {
                            f[i][j] |= f[i][j - 2];
                        }
                        // 看
                        if (i >= 1 && j >= 2 && (A.charAt(i - 1) == B.charAt(j - 2) || B.charAt(j - 2) == '.')) {
                            f[i][j] |= f[i - 1][j];
                        }
                    }
                }
            }
        }
        return f[n][m];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String A1 = "aa";
        String B1 = "a";
        System.out.println(solution.isMatch(A1, B1)); // f

        String A2 = "aa";
        String B2 = "a*";
        System.out.println(solution.isMatch(A2, B2)); // t

        String A3 = "ab";
        String B3 = ".*";
        System.out.println(solution.isMatch(A3, B3)); // t

        String A4 = "aab";
        String B4 = "c*a*b";
        System.out.println(solution.isMatch(A4, B4)); // t

        String A5 = "mississippi";
        String B5 = "mis*is*p*.";
        System.out.println(solution.isMatch(A5, B5)); // f
    }
}
