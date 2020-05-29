package LeetCodeSolution.AlgorithmThought._02_DP._343_IntegerBreak;

/*
 * 整数拆分
 *
 * 题目描述：
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。
 * 返回你可以获得的最大乘积。
 *
 * 思路：
 * 1. 尽可能分成多个 3 的和，先求出 n 所包含 3 的个数，即 n/3=a，然后再求出 3 的余数，即 n%3=b；
 * 2. 根据 3 的余数做不同的判断；
 * 3. 如果余数为 0，则说明 n 包含整数个 3，则直接返回 3^a；
 * 4. 如果余数为 1，例如 10，则需要将 3+3+3+1，转化成 3+3+4，然后再求结果；
 * 5. 如果余数为 2，例如 8，则直接返回 3^a * 2 即可。
 */
public class Solution {
    public int integerBreak(int n) {
        if (n <= 3) {
            return n - 1;
        }

        int a = n / 3;
        int b = n % 3;
        if (b == 0) {
            return (int) Math.pow(3, a);
        } else if (b == 1) {
            return (int) Math.pow(3, a - 1) * 4;
        } else {
            return (int) Math.pow(3, a) * 2;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.integerBreak(2));
        System.out.println(solution.integerBreak(3));
        System.out.println(solution.integerBreak(10));
    }
}
