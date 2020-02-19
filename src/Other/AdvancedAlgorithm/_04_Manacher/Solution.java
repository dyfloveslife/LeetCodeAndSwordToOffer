package Other.AdvancedAlgorithm._04_Manacher;

/*
 * Manacher 算法解决的问题是：在一个字符串中找到最长的回文子串
 *
 */
public class Solution {
    public static char[] manacherString(String s) {
        char[] chars = s.toCharArray();
        char[] res = new char[s.length() * 2 + 1];
        int C = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : chars[C++];
        }
        return res;
    }

    public static int maxLcpsLength(String s) {
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
        String s = "abc12343221ab";
        System.out.println(maxLcpsLength(s));
    }
}
