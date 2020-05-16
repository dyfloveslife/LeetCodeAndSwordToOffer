package LeetCodeSolution.AlgorithmThought._08_Mathematics._233_NumberOfDigitOne;

/*
 * 数字 1 的个数
 *
 * 题目描述：
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 *
 * 思路：
 * https://dyfloveslife.github.io/2019/11/28/offer-NumberOf1Between1AndN/
 */
public class Solution {

    public int countDigitOne(int n) {
        int res = 0;
        for (long i = 1; i <= n; i *= 10) {
            long a = n / i;
            long b = n % i;
            res += (a + 8) / 10 * i + (a % 10 == 1 ? b + 1 : 0);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.countDigitOne(13));
    }
}
