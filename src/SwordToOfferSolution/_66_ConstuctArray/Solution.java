package SwordToOfferSolution._66_ConstuctArray;

import java.util.Arrays;

/*
 * 构建乘积数组
 *
 * 题目描述：
 * 给定一个数组 A[0, 1, …, n-1]，请构建一个数组 B[0, 1, …, n-1]，
 * 其中 B 中的元素 B[i] = A[0] × A[1] × … × A[i-1] × A[i+1] × … × A[n-1]。
 * 不能使用除法。
 *
 * 思路：
 * 1. 要想求 B[i]，则以 i 位置将 A 划分为两部分；
 * 2. 左侧部分进行累乘，右侧部分也进行累乘，然后将这两部分的结果进行相乘即可；
 * 3. 例如计算 B[2]，左侧计算 (A[0] * A[1])，右侧计算 (A[n-1] * ... * A[3])。
 */
public class Solution {

    public int[] multiply(int[] A) {
        int len = A.length;
        int[] B = new int[len];

        B[0] = 1;
        // 计算下三角形
        for (int i = 1; i < len; i++) {
            B[i] = B[i - 1] * A[i - 1];
        }
        int temp = 1;
        // 计算上三角形
        for (int j = len - 2; j >= 0; j--) {
            temp *= A[j + 1];
            B[j] *= temp;
        }
        return B;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {1, 2, 3, 4, 5};

        System.out.println(Arrays.toString(solution.multiply(A)));
    }
}