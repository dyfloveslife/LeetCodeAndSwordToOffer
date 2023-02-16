package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._134_GasStation;

/*
 * 加油站
 *
 * 题目描述：
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 给定两个整数数组 gas 和 cost，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。如果存在解，则保证它是唯一的。
 *
 * 思路一：暴力
 * 1. 从第 0 个位置出发，走一圈看看能不能再回到 0；
 * 2. 从第 1 个位置出发，走一圈看看能不能再回到 1；
 * 3. 因为是一个圈，所以需要取余处理，例如 nums = [1, 2, 3, 4, 5]，数组长度 n = 5，当前索引 idx = 4，那么元素 5 的下一个索引应该是 nums[(idx + 1) % n]。
 *
 * 思路二：
 * 1. 存在 i < j，如果 i 能达到最远的距离是 j，那么其实 i 直接从 j + 1 开始考虑就可以了，而不是从 i + 1 开始考虑。
 */
public class Solution {
    // 思路一
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            // 当前油量
            int remain = gas[i];
            // 如果能走到下一个加油站
            while (remain - cost[j] >= 0) {
                // 下一个位置
                int next = (j + 1) % n;
                // 更新当前的油量
                remain = remain - cost[j] + gas[next];
                j = next;
                if (j == i) {
                    return i;
                }
            }
        }

        return -1;
    }

    // 思路二
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            // 当前油量
            int remain = gas[i];
            // 如果能走到下一个加油站
            while (remain - cost[j] >= 0) {
                // 下一个位置
                int next = (j + 1) % n;
                // 更新当前的油量
                remain = remain - cost[j] + gas[next];
                j = next;
                if (j == i) {
                    return i;
                }
            }
            // j 绕了一圈后又回到了 i 的前面
            if (j < i) {
                return -1;
            }
            // i 来到 j 的位置，然后 for 将 i++，实际上 i 会来到 j + 1 的位置
            i = j;
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        System.out.println(solution.canCompleteCircuit1(gas1, cost1)); // 3
        System.out.println(solution.canCompleteCircuit2(gas1, cost1)); // 3

        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println(solution.canCompleteCircuit1(gas2, cost2)); // -1
        System.out.println(solution.canCompleteCircuit2(gas2, cost2)); // -1

        int[] gas3 = {5, 1, 2, 3, 4};
        int[] cost3 = {4, 4, 1, 5, 1};
        System.out.println(solution.canCompleteCircuit1(gas3, cost3)); // 4
        System.out.println(solution.canCompleteCircuit2(gas3, cost3)); // 4
    }
}
