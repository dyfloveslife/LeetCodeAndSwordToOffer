package Other.AdvancedAlgorithm._15_MostEOR;

import java.util.HashMap;

/*
 * 求数组中的异或和为 0 的最多划分
 *
 * 题目描述
 * 给定一个整型数组arr，其中可能有正有负有零。
 * 你可以随意把整个数组切成若干个不相容的子数组，求异或和为0的子数组最多可能有多少个？
 * 整数异或和定义：把数组中所有的数异或起来得到的值。
 *
 * 思路：
 * 与《累加和等于 k 的最长子数组的长度》题目类似，只不过这里又在里面套了一个 dp.
 */
public class Solution {
    public static int mostEOR(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int res = 0;
        int xor = 0;
        int[] dp = new int[arr.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)) {
                // 之前子数组的异或和中最早的位置
                int pre = map.get(xor);
                dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
            }
            if (i > 0) {
                dp[i] = Math.max(res, dp[i]);
            }
            // 因为每次需要存的是更新后的位置，即求的是最晚的位置
            map.put(xor, i);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 9, 0, 7, 0, 2, 1, 3};
        System.out.println(mostEOR(arr));
    }
}