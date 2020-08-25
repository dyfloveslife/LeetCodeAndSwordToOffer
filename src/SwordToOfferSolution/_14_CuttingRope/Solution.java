package SwordToOfferSolution._14_CuttingRope;

/*
 * 剪绳子
 *
 * 题目描述：
 * 给你一根长度为 n 绳子，请把绳子剪成 m 段（m、n 都是整数，n>1 并且 m≥1）。
 * 每段的绳子的长度记为 k[0]、k[1]、……、k[m]。则 k[0]*k[1]*…*k[m] 可能的最大乘积是多少？
 * 例如当绳子的长度是 8 时，我们把它剪成长度分别为 2、3、3 的三段，此时得到最大的乘积 18。
 * 2 <= n <= 58
 *
 * 思路：
 * 1. 将数字 n 拆分成尽可能多的 3 之和，为什么？
 *    首先，假设 n 由 m 个数字相加而成，一般情况下，m 个数的乘积要大于原数字 n；
 *    其次，所有的数字 n 都可以通过一个因子 x 求得整数部分 a，以及求得余数部分 b，
 *          即数字 n 是由 a 个 x 和 1 个 b 相加而成。
 *          例如 n=11，11/3=3，11%3=2，则 11 是由 3 个 3 与 2 相加得到的。
 *          所以关键是分析是否存在这么一个因子 x ，满足上面的要求。
 * 2. 拆分完成后，如果余数是 1，则应该把最后的 3+1 替换成 2+2，因为 2*2 > 3*1；
 * 3. 如果余数留下的是 2，则不再拆分，因为如果将 2 再拆分成 1 和 1，则 1*1 就比 2 小了;
 * 4. 当 n 小于等于 3 时，按照上面的分析，可以直接保留原数字，但题目要求 m≥1，即最少必须拆分 1 次，
 *    因此必须拆分一个 1，即返回 n-1；
 * 5. 求 n 除以 3 的整数部分得到 a，以及余数部分得到 b；
 *    5.1) 如果 b == 0，则直接返回 3 的 a 次方；
 *    5.2) 如果 b == 1，则需要将一个 1+3 转换为 2+2，此时返回 3^(a-1) * 4；
 *    5.3) 如果 b == 2，则返回 3^a * 2。
 */
public class Solution {
    // 暴力递归
    public int cuttingRopeSolution0(int n) {
        if (n == 2) {
            return 1;
        }
        int ans = -1;
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, Math.max(i * cuttingRopeSolution0(n - i), i * (n - i)));
        }
        return ans;
    }

    /*
     * 动态规划，也就是从 F(2) 逐步迭代到 F(n)，自底向上的过程
     * 0  1  2  3  4  5
     * 0  1  2  3 (4) (6)
     * dp[i] 表示长度为 i 的绳子的最大乘积；
     * dp[i] = Math.max(dp[i], Math.max((i - j) * j), j * dp[i - j]))
     * (i - j) * j 表示从 j 处剪一下，剪下来的部分是 i-j，然后 i-j 就不再剪了；
     * j * dp[i - j]) 表示从 j 处剪一下，剪下来的部分是 i-j，然后 i-j 还要继续剪
     */
    public int cuttingRopeSolution11(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, j * dp[i - j]));
            }
        }
        return dp[n];
    }

    public int cuttingRopeSolution1(int n) {
        if (n < 2) {
            return 0;
        }
        if (n == 2 || n == 3) {
            return n - 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }
        return dp[n];
    }

    // 数学公式推导
    public int cuttingRopeSolution2(int n) {
        if (n <= 3) {
            return n - 1;
        }

        int a = n / 3;
        int b = n % 3;
        if (b == 0) {
            return (int) Math.pow(3, a);
        }
        if (b == 1) {
            return (int) (Math.pow(3, a - 1) * 4);
        }
        return (int) (Math.pow(3, a) * 2);
    }

    // 贪心
    public int cuttingRopeSolution3(int n) {
        if (n <= 3) {
            return n - 1;
        }

        int res = 1;
        while (n > 4) {
            res *= 3;
            n -= 3;
        }
        return res * n;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.cuttingRopeSolution0(4));
        System.out.println(solution.cuttingRopeSolution0(8));
        System.out.println(solution.cuttingRopeSolution0(10));
        System.out.println("====");
        System.out.println(solution.cuttingRopeSolution1(4));
        System.out.println(solution.cuttingRopeSolution1(8));
        System.out.println(solution.cuttingRopeSolution1(10));
        System.out.println("====");
        System.out.println(solution.cuttingRopeSolution11(4));
        System.out.println(solution.cuttingRopeSolution11(8));
        System.out.println(solution.cuttingRopeSolution11(10));
        System.out.println("====");
        System.out.println(solution.cuttingRopeSolution2(4));
        System.out.println(solution.cuttingRopeSolution2(8));
        System.out.println(solution.cuttingRopeSolution2(10));
        System.out.println("====");
        System.out.println(solution.cuttingRopeSolution3(4));
        System.out.println(solution.cuttingRopeSolution3(8));
        System.out.println(solution.cuttingRopeSolution3(10));
    }
}