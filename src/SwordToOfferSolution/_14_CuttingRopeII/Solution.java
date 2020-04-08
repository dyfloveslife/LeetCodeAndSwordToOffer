package SwordToOfferSolution._14_CuttingRopeII;

/*
 * 剪绳子 Ⅱ
 *
 * 题目描述：
 * 给你一根长度为 n 绳子，请把绳子剪成 m 段（m、n 都是整数，n>1 并且 m≥1）。
 * 每段的绳子的长度记为 k[0]、k[1]、……、k[m]。则 k[0]*k[1]*…*k[m] 可能的最大乘积是多少？
 * 例如当绳子的长度是 8 时，我们把它剪成长度分别为 2、3、3 的三段，此时得到最大的乘积 18。
 * 注意：答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 思路：
 * 1. 和剪绳子Ⅰ是相同的问题，只不过这里涉及到大数取余的问题；
 * 2. 由于中间结果 res 需要存储大数，所以定义为 long 类型；
 * 3. 然后对中间结果取余即可。
 */
public class Solution {

    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }

        long res = 1;
        while (n > 4) {
            res *= 3;
            res %= 1000000007;
            n -= 3;
        }
        return (int) (res * n % 1000000007);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.cuttingRope(3));
        System.out.println(solution.cuttingRope(173767893));
        System.out.println(solution.cuttingRope(1000000008));
    }
}
