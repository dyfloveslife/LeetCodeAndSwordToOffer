package SwordToOfferSolution._15_NumberOf1InBinary;

/*
 * 二进制中 1 的个数
 *
 * 题目描述：
 * 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。
 * 例如把 9 表示成二进制是 1001，有两位是 1。因此如果输入 9，该函数输出 2。
 *
 */
public class Solution {
    private static int numberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int i = numberOf1(7);
        System.out.println(i);
    }
}
