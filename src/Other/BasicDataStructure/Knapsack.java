package Other.BasicDataStructure;

/**
 * N = 3, W = 4
 * wt = [2, 1, 3]
 * val = [4, 2, 3]
 */
public class Knapsack {

    public static int method(int N, int W, int[] wt, int[] val) {
        int[][] dp = new int[N + 1][W + 1];

        for (int i = 0; i < N + 1; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < W + 1; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                // 背包总容量装不下重量为 wt[i - 1] 的物品
                if (W - wt[i - 1] < 0) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // 装或者不装，择优
                    dp[i][w] = Math.max(dp[i - 1][w],
                            dp[i - 1][W - wt[i - 1]] + val[i - 1]);
                }
            }
        }
        return dp[N - 1][W - 1];
    }

    public static void main(String[] args) {
        int N = 3;
        int W = 4;
        int[] wt = {2, 1, 3};
        int[] val = {4, 2, 3};
        System.out.println(method(N, W, wt, val));
    }
}
