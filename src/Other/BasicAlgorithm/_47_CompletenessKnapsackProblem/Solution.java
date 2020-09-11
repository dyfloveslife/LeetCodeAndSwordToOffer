package Other.BasicAlgorithm._47_CompletenessKnapsackProblem;

import java.util.Scanner;

/*
 * 完全背包问题
 *
 * 思路：
 * 1. 与之前的 0-1 背包的思路相同，但是在内层循环的时候，需要从小到大进行。
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int V = sc.nextInt();
        int[] v = new int[N + 1];
        int[] w = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }

        int[] dp = new int[V + 1];
        for (int i = 1; i <= N; i++) {
            // 仅对内层 for 循环进行修改即可
            for (int j = v[i]; j <= V; j++) {
                dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
            }
        }
        System.out.println(dp[V]);
    }
}
