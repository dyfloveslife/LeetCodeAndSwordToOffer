package Other.AdvancedAlgorithm._02_KMP_ShortestContainTwiceOriginalString;

/*
 * 题目描述：
 * 给定一个原始串，只能在其后面添加字符，要求生成一个大字符串，
 * 该大字符串必须包含两个原始串，而且两个串的开头位置不能相同。
 * 怎样处理使得大字符串更短？
 *
 * 例如：
 *     输入：abcdabcd    输出：abcdabcdabcd
 *     输入：abracadabra 输出：abracadabracadabra
 *
 * 思路：
 * 使用 KMP 算法，只不过在 next 数组中再多求一位即可。
 */
public class Solution {
    public String answer(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        char[] chars = s.toCharArray();
        if (chars.length == 1) {
            return s + s;
        }
        if (chars.length == 2) {
            return chars[0] == chars[1] ? (s + chars[0]) : (s + s);
        }

        int endNext = endNextLength(chars);
        return s + s.substring(endNext);
    }

    public int endNextLength(char[] chars) {
        int[] next = new int[chars.length + 1];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < next.length) {
            if (chars[pos - 1] == chars[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next[next.length - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "abcdabcd";
        String s2 = "abracadabra";

        System.out.println(solution.answer(s1));
        System.out.println(solution.answer(s2));
    }
}
