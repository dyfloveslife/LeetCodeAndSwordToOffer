package LeetCodeSolution.AlgorithmThought._02_DP._413_ArithmeticSlices;

/*
 * 等差数列划分
 *
 * 题目描述：
 * 如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * 数组 A 包含 N 个数，且索引从 0 开始。数组 A 的一个子数组划分为数组 (P, Q)，P 与 Q 是整数且满足 0<=P<Q<N。
 *
 * 如果满足以下条件，则称子数组 (P, Q) 为等差数组：
 *     元素 A[P], A[p + 1], ..., A[Q - 1], A[Q] 是等差的。并且 P + 1 < Q；
 *     函数要返回数组 A 中所有为等差数组的子数组个数。
 *
 * 思路：
 * 1. dp[i] 表示以 A[i] 为结尾的等差递增子区间的个数；
 * 2. 当 A[i]-A[i-1] == A[i-1]-A[i-2] 的时候，A[i-2]、A[i-1]、A[i] 构成一组等差递增子区间；
 * 3. 此外，在 A[i-1]后面再加上一个 A[i]，同样可以构成新的递增子区间；
 * 4. 以 A = [0, 1, 2, 3, 4] 为例：
 *       dp[2] = 1 表示以 A[2]=2 为结尾的等差递增子区间的个数为 1 个，即 [0, 1, 2]；
 *       dp[3] = dp[2]+1 表示以 A[3] 为结尾的等差递增子区间的个数为 2 个，它是在 dp[2] 的基础上加 1 得到的，
 *             即 [0, 1, 2, 3] 是在 [0, 1, 2] 的基础上加了一个 3；
 *             即 [1, 2, 3] 表示新增的递增子区间。
 * 5. 注意题目要最后要求的结果：即 A 中所有为等差数组的子数组的个数，由于递增子区间不一定是以最后一个元素结尾的，
 *    可以是任意一个元素结尾，因此需要返回 dp 数组累加的结果。
 */
public class Solution {

    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int[] dp = new int[A.length];
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = dp[i - 1] + 1;
            }
        }

        int res = 0;
        for (int num : dp) {
            res += num;
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {1, 2, 3, 4};

        System.out.println(solution.numberOfArithmeticSlices(A));
    }
}
