package Other.AdvancedAlgorithm._05_Manacher_ShortestEnd;

/*
 * 一个字符串只能向后面添加字符，怎么让整个串变成回文串？并且添加的字符最短
 *
 * 例如，输入字符串 abcd123321，输出 dcba，因为只需要在输入的字符串后面添加上 dcba 就可以满足题意。
 */
public class Solution {
    public char[] manacherString(String s) {
        char[] chars = s.toCharArray();
        char[] res = new char[s.length() * 2 + 1];
        int C = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : chars[C++];
        }
        return res;
    }

    public String shortestEnd(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        char[] charArr = manacherString(s);
        int[] pArr = new int[charArr.length];
        int C = -1;
        int R = -1;
        int maxContainsEnd = -1;

        for (int i = 0; i != charArr.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            if (R == charArr.length) {
                maxContainsEnd = pArr[i];
                break;
            }
        }
        char[] res = new char[s.length() - maxContainsEnd + 1];
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1 - i] = charArr[i * 2 + 1];
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "abcd123321";

        System.out.println(solution.shortestEnd(s));
    }
}
