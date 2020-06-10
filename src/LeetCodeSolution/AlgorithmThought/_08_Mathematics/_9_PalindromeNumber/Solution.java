package LeetCodeSolution.AlgorithmThought._08_Mathematics._9_PalindromeNumber;

/*
 * 回文数
 *
 * 题目描述：
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 你可以不将整数转为字符串来解决这个问题吗？
 *
 * 思路：
 * 1. 一开始想到的是将数字转换成字符串，通过双指针来解决，但看了一下【进阶】的要求，不能转换成字符串；
 * 2. 然后就想到求数字的逆序，通过逆序来与原数字进行比较即可。
 */
public class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int res = 0;
        int num = x;

        while (num != 0) {
            // 乘以 10 是为了给个位留出空位
            // 取余就是求出个位
            res = res * 10 + num % 10;
            num /= 10;
        }

        return res == x;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.isPalindrome(123));
        System.out.println(solution.isPalindrome(121));
    }
}
