package LeetCodeSolution.AlgorithmThought._02_DP._646_MaximumLengthOfPairChain;

import java.util.Arrays;

/*
 * 最长数对链
 *
 * 题目描述：
 * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 * 给定一个对数集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 *
 * 思路：
 * 1. 该题和“最长上升子序列”的思路是一样的，需要维护的就是当前数对的第二个元素和下一个数对的第一个元素的大小关系；
 * 2. 需要注意的是，最好先将给定的矩阵进行排序，即按照每个数对的第一个元素的大小关系进行排序；
 * 3. 剩下的就是填充 dp 数组的过程。
 */
public class Solution {

    // DP
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0 || pairs[0].length == 0) {
            return 0;
        }

        Arrays.sort(pairs, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o1[1] - o2[1];
            }
        });

        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < pairs.length; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int num : dp) {
            res = Math.max(res, num);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] pairs = {{1, 2}, {2, 3}, {3, 4}};

        System.out.println(solution.findLongestChain(pairs));
    }
}
