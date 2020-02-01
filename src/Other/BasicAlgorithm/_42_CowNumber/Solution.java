package Other.BasicAlgorithm._42_CowNumber;

/*
 * 母牛生崽问题
 *
 * 问题描述：
 * 母牛每年生一只母牛，新出上的母牛成长三年后也能每年生一只母牛。
 * 假设不死亡，求 N 年后母牛的数量。
 *
 * 思路：
 *
 * 母牛： A    A    A    A    A    A
 * 母牛：      B    B    B    B    B
 * 母牛：           C    C    C    C
 * 母牛：                D    D    D
 * 母牛：                     E    E
 * 母牛：                     F    F
 * 母牛：                          G
 * 母牛：                          H
 * 母牛：                          I
 * 年数： 1    2    3    4    5    6
 * 数量： 1    2    3    4    6    9
 *
 * 从第 4 年开始，可得 F(N)=F(N-1)+F(N-3)。
 * 也就是说，今年的牛等于去年的牛再加上新生的牛。三年之内的牛还不能生牛，所以中间空出来了。
 *
 * 还可以改成 DP。
 */
public class Solution {

    public static int cowNumber(int n) {
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return cowNumber(n - 1) + cowNumber(n - 3);
    }

    public static void main(String[] args) {
        System.out.println(cowNumber(6));
    }
}
