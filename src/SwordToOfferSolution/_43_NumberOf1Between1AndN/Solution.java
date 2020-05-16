package SwordToOfferSolution._43_NumberOf1Between1AndN;

/*
 * 从整数 1 到 n 中 1 出现的次数
 *
 * 题目描述：
 * 输入一个整数 n，求从 1 到 n 这 n 个整数的十进制表示中 1 出现的次数。
 * 例如输入 12，从 1 到 12 这些整数中包含 1 的数字有 1，10，11 和 12，其中 1 一共出现了 5 次。
 *
 * 思路：
 * 找数字的规律
 * https://dyfloveslife.github.io/2019/11/28/offer-NumberOf1Between1AndN/
 */
public class Solution {
    public int countDigitOne(int n) {
        int res = 0;
        for (long m = 1; m <= n; m *= 10) {
            long a = n / m, b = n % m;
            res += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.countDigitOne(12));
        System.out.println(solution.countDigitOne(13));
    }
}
