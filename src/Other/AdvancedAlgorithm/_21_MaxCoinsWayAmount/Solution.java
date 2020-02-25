package Other.AdvancedAlgorithm._21_MaxCoinsWayAmount;

import java.util.HashMap;

/*
 * 最多的换钱方法
 */
public class Solution {

    // 暴力
    public static int way(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
//        return process(arr, 0, aim);
//        return process_map(arr, 0, aim);
        return process_dp(arr, 0, aim);
    }

    // index: 可以任意使用 index 及其之后的所有的钱
    private static int process(int[] arr, int index, int aim) {
        int res = 0;
        // 如果来到了数组的最后且已经没有货币了，剩的钱数是 0 的话，则是一种有效的划分，则返回 1
        // 如果来到了数组的最后且已经没有货币了，还有钱剩下的话，则说明之前做出的选择不是有效的
        // 那么就没有找到一种有效的方式，则返回 0
        if (index == arr.length) {
            res = (aim == 0) ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                res += process(arr, index + 1, aim - arr[index] * i);
            }
        }
        return res;
    }

    // 带有备忘录的递归 DP
    public static HashMap<String, Integer> map = new HashMap<>();

    public static int process_map(int[] arr, int index, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        int res = 0;
        if (index == arr.length) {
            res = (aim == 0) ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                int nextAim = aim - arr[index] * i;
                String key = (index + 1) + "_" + nextAim;
                if (map.containsKey(key)) {
                    res += map.get(key);
                } else {
                    res += process_map(arr, index + 1, nextAim);
                }
            }
        }
        return res;
    }

    // DP
    public static int process_dp(int[] arr, int index, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += (j - arr[i] >= 0) ? dp[i][j - arr[i]] : 0;
            }
        }
        return dp[arr.length - 1][aim];
    }


    public static void main(String[] args) {
        int[] arr = {5, 10, 25, 1};
        System.out.println(way(arr, 15));
    }
}
