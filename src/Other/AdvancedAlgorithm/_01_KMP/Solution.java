package Other.AdvancedAlgorithm._01_KMP;

/*
 * KMP
 *
 * 思路：
 * 1. 求 str2 的最长前缀和最长后缀的匹配长度，即 next 数组；
 * 2. 使用 next 加速匹配过程，即如果当前字符不相等的话，直接往后移动当前字符对应数组中的长度；
 * 3. 注意求 next 数组的方法。
 */
public class Solution {
    public int getIndexOf(String s, String m) {
        if (s == null || m == null || s.length() < 1 || s.length() < m.length()) {
            return -1;
        }

        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNextArray(str2);
        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
                // 如果对应的字符不想等的话，此时 i2 应该往前跳
                // 假如 i2 已经来到了 str2 中 0 位置的字符
                // 则 str1 中的 i1 往后移动，继续比较 str1 中的 i1+1 与 str2 中的 0 位置上的字符
            } else if (next[i2] == -1) {
                i1++;
            } else {
                i2 = next[i2];
            }
        }
        // 如果 i2 已经滑过了整个 str2，说明已经匹配上了
        return i2 == str2.length ? i1 - i2 : -1;
    }

    private int[] getNextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0; // 往前跳的索引
        while (i < next.length) {
            // 如果当前跳到的位置和前一个字符相同
            // 则当前位置的值就是 cn+1 的值，然后 i 继续往后移动
            if (str2[i - 1] == str2[cn]) {
                next[i++] = ++cn;
                // 如果当前跳到的位置和前一个字符不同
                // 分为可以再往前跳和不能再往前跳
                // 还可以往前跳
            } else if (cn > 0) {
                cn = next[cn];
                // 不能往前跳了
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str1 = "BBCABCDABABCDABCDABDE";
        String str2 = "ABCDABD";

        System.out.println(solution.getIndexOf(str1, str2));
    }
}
