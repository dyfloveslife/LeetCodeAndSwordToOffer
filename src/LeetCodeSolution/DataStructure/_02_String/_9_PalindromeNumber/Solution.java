package LeetCodeSolution.DataStructure._02_String._9_PalindromeNumber;

/*
 * 回文数
 *
 * 题目描述：
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 思路：
 * 1. 这里不将给定的整数转化成字符串进行比较，而是直接通过取余、整除的方式构建一个逆序的 x；
 * 2. 最后再通过逆序后的结果与 x 进行比较，相等则为回文数。
 */
public class Solution {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int num = x;
        int res = 0;
        while (num != 0) {
            // 每次通过乘 10 加余数的方式，将 x 逆序构建出来
            res = res * 10 + num % 10;
            num /= 10;
        }
        // 最后判断是否和给定的 x 相等，相等则为回文数
        return res == x;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int x1 = 121;
        int x2 = 20;

        System.out.println(solution.isPalindrome(x1));
        System.out.println(solution.isPalindrome(x2));
    }
}
