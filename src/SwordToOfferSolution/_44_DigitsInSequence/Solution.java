package SwordToOfferSolution._44_DigitsInSequence;

/*
 * 数字序列中的某一位数字
 *
 * 题目描述：
 * 数字以 0123456789101112131415… 的格式序列化到一个字符序列中。
 * 在这个序列中，第 5 位（从 0 开始计数）是 5，第 13 位是 1，第 19 位是 4 等等。
 * 请写一个函数求任意位对应的数字。
 *
 * 思路：
 * https://dyfloveslife.github.io/2019/11/29/offer-DigitsInSequence/
 */
public class Solution {
    public static int digitsInSequence(int[] arr, int n) {
        if (n < 0) {
            return -1;
        }
        if (n <= 9) {
            return n;
        }

        int index = 0;
        for (int i = 1; i <= 9; i++) {
            if (arr[i] >= n) {
                index = i;
                break;
            }
        }
        n = n - arr[index - 1];
        int temp1 = n / index;
        int temp2 = n % index;
        int temp3 = (int) (temp1 + Math.pow(10, index - 1));
        return (int) (temp3 / Math.pow(10, index - temp2 - 1)) % 10;
    }

    public static void main(String[] args) {
        int[] arr = {0, 10, 190, 2890, 38890, 488890, 5888890, 68888890, 788888890};
        System.out.println(digitsInSequence(arr, 11));
    }
}