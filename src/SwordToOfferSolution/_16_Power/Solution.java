package SwordToOfferSolution._16_Power;

/*
 * 数值的整数次方
 *
 * 题目描述：
 * 实现函数 double Power(double base, int exponent)，求 base 的 exponent 次方。
 * 不得使用库函数，同时不需要考虑大数问题。
 * 注意：-100.0 < x < 100.0
 *       n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 *
 * 思路：
 * 1. 将指数 n 做二进制分解，在底数不断自身乘以自身的过程中，将最终结果需要的部分保存下来；
 * 2. 例如 18 的二进制为 10010，即 18 = 1 × 2^4 + 0 × 2^3 + 0 × 2^2 + 1 × 2^1 + 0 × 2^0；
 * 3. 其中，系数可以通过对二进制 10010 不断右移得到；
 * 4. 而指数可以通过底数不断累乘得到；
 * 5. 注意：由于指数 n 可以取到 -2^31 = -2147483648，即整数范围内的最小值，
 *    因此，需要将 n 转换成 long 型。
 */
public class Solution {

    public double myPower(double x, int n) {
        long N = n;

        if (N == 0) {
            return 1;
        }
        if (N < 0) {
            N = -N;
            x = 1 / x;
        }

        double res = 1.0;
        while (N > 0) {
            // 如果 N 是奇数，则需要先乘以一个底数
            if ((N & 1) == 1) {
                res *= x;
            }
            x *= x;
            N >>= 1;
        }
        return res;
    }
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.myPower(2.00000, 10));
        System.out.println(solution.myPower(2.10000, 3));
        System.out.println(solution.myPower(2.00000, -2));
        System.out.println(solution.myPower(2.00000, -2147483648));
    }
}
