package LeetCodeSolution.AlgorithmThought._02_DP._72_EditDistance;

/*
 * 编辑距离
 *
 * 题目描述：
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 *     插入一个字符
 *     删除一个字符
 *     替换一个字符
 *
 * 思路：
 * 1. 总体的思想是：使用两个指针 i、j 分别指向字符串的最后，然后一步一步的往前走，缩小问题的规模；
 * 2. 如果 i 和 j 所指的字符相同，为了满足最少的操作次数，则应直接往前移动 i 和 j，不应该对它们所指的字符进行操作；
 * 3. 如果 j 走完了 word2，而 i 还没走完 word1，则只能使用删除操作将 word1 缩短为 word2；
 * 4. 如果 i 走完了 word1，而 j 还没走完 word2，则只能使用插入操作将 word2 剩下的字符全部插入到 word1 中；
 * 5. 整体框架：
 *    if (word1[i] == word2[j]):
 *        // 什么都不做，i 和 j 都向前移动
 *        i--;
 *        j--;
 *    else:
 *        // 三选一
 *        // 插入
 *        // 删除
 *        // 替换
 * 6. 定义状态：dp[i][j] 表示返回 word1[0...i] 和 word2[0...j] 的最小编辑距离；
 * 7. 如果 word1[i] == word2[j]，则返回 dp[i - 1][j - 1]，即当前字符对应相等，不需要进行操作，
 *    而当前对应的最小编辑距离就是上一个状态的最小编辑距离；
 * 8. 如果 word1[i] != word2[j]，则需要分为三种情况：
 *    8.1) 插入一个字符：直接在 word1[i] 位置插入一个和 word2[j] 一样的字符，此时 word2[j] 就匹配了，然后前移 j，
 *         继续和 i 比较，同时操作数加一；
 *    8.2) 删除一个字符：直接把 word1[i] 位置的字符删除，前移 i，继续和 j 位置的字符比较，同时操作数加一；
 *    8.3) 替换一个字符：直接把 word1[i] 字符替换成 word2[j] 字符，这两个位置就匹配成功了，然后同时前移 i 和 j，
 *         同时操作数加一。
 */
public class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];
        // 初始化边界
        // 先初始化列，因为这里的边界是 len1，表示的是行的范围
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        // 再初始化行，这里的 len2 表示的是列的范围
        for (int i = 1; i <= len2; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1,
                            dp[i - 1][j - 1] + 1
                    );
                }
            }
        }
        return dp[len1][len2];
    }

    public int min(int x, int y, int z) {
        return Math.min(Math.min(x, y), z);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String word1 = "horse";
        String word2 = "ros";
        System.out.println(solution.minDistance(word1, word2));
    }
}
