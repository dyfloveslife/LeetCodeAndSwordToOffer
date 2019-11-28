package SwordToOfferSolution._43_NumberOf1Between1AndN;

/**
 * 从整数 1 到 n 中 1 出现的次数
 * 思路：
 * 找数字的规律
 *
 */
public class Solution {
    public int numberOf1Between1AndN_Solution(int n) {
        int res = 0;

        for (int m = 1; m <= n; m *= 10) {
            int a = n / m, b = n % m;
            res += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
        }
        return res;

    }
}
