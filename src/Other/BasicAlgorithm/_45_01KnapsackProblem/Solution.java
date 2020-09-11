package Other.BasicAlgorithm._45_01KnapsackProblem;

import java.util.Scanner;

/*
 * 0-1 背包问题
 *
 * 这里总结一下 0-1 背包问题，笔试的时候经常作为第一道题出现。
 *
 * 题目描述：
 * input：
 * 100    // 背包的容量
 * 5      // 物品的数量
 * 77 92  // 每个物品的体积与价值
 * 22 22
 * 29 36
 * 50 46
 * 99 90
 *
 * output：
 * 114   // 最大价值
 *
 * 思路：
 * 参考：https://www.acwing.com/solution/content/1644/
 * 0. 对于 0-1 背包问题就是说，对于第 i 个物品，只能有拿或不拿两种方式（状态）；
 * 1. 首先是二维 dp，定义 dp[i][j] 表示对于前 i 件物品来说，背包容量为 j 的情况下，背包中的物品所能达到的最大价值；
 * 2. dp[i][j] 有两种状态：
 *    2.1) 不选择当前第 i 件物品（或者第 i 件物品的体积大于了背包的容量），这种情况下就是说，不把第 i 件物品放进背包中，
 *         那么背包中的最大价值为 dp[i][j] = dp[i - 1][j]，也就是当前的状态等于前一个状态；
 *    2.2) 选择当前第 i 件物品（隐含的条件是：当前第 i 件物品的体积小于等于背包容量），这种情况下可以将第 i 件物品放进背包中，
 *         那么背包中的最大价值为：当前物品的价值再加上背包剩余容量在只能选择前 i-1 件物品的情况下的最大价值，即
 *         dp[i][j] = w[i] + dp[i - 1][j - v[i]]
 * 3. 所以，dp[i][j] 需要在这两者之间选择最大的那个，即为最优解；
 */
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // V 表示背包的容量
        int V = sc.nextInt();
        // N 表示物品的数量
        int N = sc.nextInt();
        // v 和 w 分别表示第 i 个物品的体积和价值
        int[] v = new int[N + 1];
        int[] w = new int[N + 1];
        // 以此从控制台读入每个物品的体积和价值，不断填充数组 v 和 w
        for (int i = 1; i <= N; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }

        // 对于前 i 件物品来说，背包容量为 j 的情况下，背包中的物品所能达到的最大价值
        // 注意：这里定义 N+1 和 V+1 的意思是说：因为第 0 行表示只能选择第 0 个物品，也就是当没有物品的时候，
        // 第 0 列表示背包的体积，也就是不能装任何东西的，所以需要将 dp[0][0] 初始化为 0
        int[][] dp = new int[N + 1][V + 1];
        dp[0][0] = 0;
        // i 表示第 i 个物品
        for (int i = 1; i <= N; i++) {
            // j 表示背包的容量
            for (int j = 0; j <= V; j++) {
                // 如果第 i 件物品的体积小于背包容量的话，则说明可以将第 i 件物品放进背包中
                if (j >= v[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - v[i]] + w[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[N][V]);
    }
}
