package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._1049_LastStoneWeightII;

/*
 * 最后一块石头的重量 Ⅱ
 *
 * 题目描述：
 * 有一堆石头，每块石头的重量都是正整数。
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。
 * 注意：返回此石头最小的可能重量。
 * 如果没有石头剩下，就返回 0。
 *
 * 思路：
 * 1. 0-1 背包问题；
 * 2. 可以将石头分成两个堆，由于拿走两个石头以后，还有机会将石头放回去，因此可以看成两个堆；
 * 3. 总重量为 sum，则需要计算的就是如果使两个堆的总重量接近 sum/2；
 * 4. dp[i] 表示背包容量限制为 i 时能够装载的最大石头的重量；
 * 5. 那么就可以分为拿或者不拿。
 */
public class Solution {
    public int lastStoneWeightII(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }

        // 获取石头的总重量
        int sum = 0;
        for (int num : stones) {
            sum += num;
        }

        int max = sum / 2;
        int[] dp = new int[max + 1];
        for (int i = 0; i < stones.length; i++) {
            int curStone = stones[i];
            for (int j = max; j >= curStone; j--) {
                dp[j] = Math.max(dp[j], dp[j - curStone] + curStone);
            }
        }
        return sum - 2 * dp[max];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] stones = {2, 7, 4, 1, 8, 1};

        System.out.println(solution.lastStoneWeightII(stones));
    }
}
