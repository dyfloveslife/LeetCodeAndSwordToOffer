package LeetCodeSolution.AlgorithmThought._08_Mathematics._400_NthDigit;

/*
 * 第 N 个数字
 *
 * 题目描述：
 * 数字以 0123456789101112131415… 的格式序列化到一个字符序列中。
 * 在这个序列中，第 5 位（从下标 0 开始计数）是 5，第 13 位是 1，第 19 位是 4，等等。
 *
 * 思路：
 * 0. 关键就是找第 n 个数字在哪个整数上，例如第 11 个数字是整数 10 的 0 部分；
 * 1. 对于给定的 n：
 *    首先确定 n 一共有几位数，例如 11 是 2 位数，123 是 3 位数，2414 是 4 位数，记 digit；
 *    然后确定 n 所在的数字，记 num；
 *    最后确定 n 是 num 中的哪一位，并将结果返回。
 * 2. 例如，2901 = 9 + 180 + 2700 + 12，是 4 位数，第 12 位；
 * 3. 在哪个整数中？1000 + (12 - 1) / 4 = 1000 + 2 = 1002；
 * 4. 然后在 1002 中确定最终是哪一位？即 (n - 1) % 4 = 3，s.charAt(3) = 2。
 * 5. https://i.loli.net/2020/05/19/WSs1DrnChpPFJV6.png
 */
public class Solution {
    public int findNthDigit(int n) {
        // n 所在数字的位数
        int digit = 1;
        // 数字范围开始的第一个数
        long start = 1;
        // 占多少位
        long count = 9;
        while (n > count) {
            n -= count;
            digit++;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit;
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findNthDigit(2901));
    }
}
