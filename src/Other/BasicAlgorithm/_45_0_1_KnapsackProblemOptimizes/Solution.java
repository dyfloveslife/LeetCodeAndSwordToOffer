package Other.BasicAlgorithm._45_0_1_KnapsackProblemOptimizes;

import java.util.Scanner;

/*
 * 我们来考虑如何将 0-1 背包从二维 dp 优化到 一维 dp
 *
 * 1. 还是状态转移方程：dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - v[i]] + w[i])；
 * 2. 第二层循环需要从大到小循环，这是为了避免 i-1 的值被覆盖；
 * 3. 也就是先更新第 i 个的值，然后再更新第 i-1 个值。
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

        // 注意这里是容量再加 1
        int[] dp = new int[V + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            // 背包容量大于当前物品的体积的时候，才能更新 dp 数组
            for (int j =  V; j >= v[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
            }
        }
        System.out.println(dp[V]);
    }
}
