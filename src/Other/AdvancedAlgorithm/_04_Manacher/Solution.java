package Other.AdvancedAlgorithm._04_Manacher;

import java.math.BigDecimal;

/*
 * 最长回文子串
 *
 * 题目描述：
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 思路一：暴力
 * 1. 第一种的暴力写法应该挺容易想到，就是 i 从 0 到 s.length() 遍历，j 从 i 开始到 s.length() 结束；
 * 2. 也就是枚举所有的字符串，判断该字符串是否符合回文字符串，如果符合，将不断地跟新最大回文字符串的长度，直至结束；
 * 3. 记录的时候，只需要记录当前子串的起始位置和子串的长度，用于结果的返回。
 *
 * 思路二：DP
 * 1. 从回文串的定义开始讨论：给定一个字符串，只有在首尾字符相等的情况下，里面子串的回文性质决定了整个子串的回文性质；
 * 2. 状态转移方程：dp[i][j] 表示子串 s[i...j] 是否为回文子串，左右全闭；
 *    2.1) 根据头尾字符是否相等，可以得到 dp[i][j] = (s[i] == s[j]) and dp[i + 1][j - 1]；
 *    2.2) 也就是说，在首尾字符相等的情况下，再向里面取边界，然后再看是否满足回文子串的定义；
 *    2.3) 当 [i + 1, j - 1] 不构成区间时，即长度严格小于 2 时，也就是说，j - 1 - (i + 1) + 1 < 2，得到 j - i < 3；
 *    2.4) 以上结果等价于 j - i + 1 < 4，即只要子串 s[i...j] 的长度等于 2 或等于 3 的时候，就只需要判断一下头尾两个字符是否
 *    相等就可以直接下结论了；
 *    2.5) 如果子串 s[i + 1...j - 1] 只有 1 个字符，显然是回文串；
 *         如果子串 s[i + 1...j - 1] 为空串，那么子串 s[i, j] 一定是回文串；
 *    2.6) 因此，在 s[i] == s[j] 成立和 j - i < 3 的前提下，可以直接得到 dp[i][j] = true。
 * 3. 初始化的时候，只有一个字符的时候肯定是回文的，因此对角线上的 dp[i][i] 都是 true；
 * 4. 只要一得到 dp[i][j] = true，就记录子串的长度和起始位置；
 * 5. 需要注意的是，总是需要先得到小的子串的回文判定，然后大的子串才能参考小的子串得到判定结果。
 *
 * 思路四：Manacher
 * 1. 该算法解决的问题是：在一个字符串中找到最长的回文子串；
 * 2. 详见脑图。
 */
public class Solution {
    // 思路一
    public String longestPalindrome1(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int maxLen = 1;
        int begin = 0;
        char[] chars = s.toCharArray();

        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i; j < s.length(); j++) {
                if (j - i + 1 > maxLen && isPalindrome(chars, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    private boolean isPalindrome(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // 思路二：DP
    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // dp[i][j] 表示 s[i][j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] chars = s.toCharArray();

        // 对角线上的值
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                // 只要 dp[i][j] == true 成立，就说明子串 s[i...j] 是回文串，
                // 此时记录回文串的长度的和起始位置，用于最后的结果返回
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    // 思路三
    public char[] manacherString(String s) {
        char[] chars = s.toCharArray();
        char[] res = new char[s.length() * 2 + 1];
        int C = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : chars[C++];
        }
        return res;
    }

    public int longestPalindrome3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] charArr = manacherString(s);
        // 回文半径数组
        int[] pArr = new int[charArr.length];
        int C = -1;
        int R = -1;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i != charArr.length; i++) {
            // R > i 表示 R 在回文右边界的里面
            // 2*C-i 表示关于 i 的对称点 i撇
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            // 不管哪种情况，都往右扩一下
            // 但对于情况二和情况三，则一定不会成立
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            // 如果扩出来的字符也属于回文中的一部分，
            // 则就更新回文右边界
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "abc12343221ab";
        String s2 = "babad";
        String s3 = "cbbd";

        System.out.println(solution.longestPalindrome1(s1));
        System.out.println(solution.longestPalindrome1(s2));
        System.out.println(solution.longestPalindrome1(s3));
        System.out.println("===");

        System.out.println(solution.longestPalindrome2(s1));
        System.out.println(solution.longestPalindrome2(s2));
        System.out.println(solution.longestPalindrome2(s3));
        System.out.println("===");

        System.out.println(solution.longestPalindrome3(s1));
        System.out.println(solution.longestPalindrome3(s2));
        System.out.println(solution.longestPalindrome3(s3));
    }
}