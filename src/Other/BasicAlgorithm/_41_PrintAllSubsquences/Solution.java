package Other.BasicAlgorithm._41_PrintAllSubsquences;

/*
 * 打印一个字符串的全部子序列，包括空字符串
 *
 * 思路：
 * 1. 可以使用递归；
 * 2. 例如 abc，0 位置有两中选择，即要 a 和不要 a；
 * 3. 如果要 a，则在位置 1 上的就变成了 a，如果不要 a，则在位置 1 上的就还是原来的空字符串；
 * 4. 在位置 1 上有了 a 和空字符串，这两个位置分别可以有两个分支，即考虑要不要 b；
 * 5. 以此类推，穷尽所有的情况。
 */
public class Solution {

    // res 表示上级给我的字符串
    public static void printAllSubsquences(char[] chars, int i, String res) {
        // base case
        // 当来到最后的时候，直接将得到的东西打印
        if (i == chars.length) {
            System.out.println(res);
            return ;
        }
        // 不要当前字符
        printAllSubsquences(chars, i + 1, res);
        // 要当前字符
        printAllSubsquences(chars, i + 1, res + chars[i]);
    }

    public static void main(String[] args) {
        String str = "abc";
        printAllSubsquences(str.toCharArray(), 0, "");
    }
}
