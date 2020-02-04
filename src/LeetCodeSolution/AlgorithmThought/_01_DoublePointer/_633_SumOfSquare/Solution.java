package LeetCodeSolution.AlgorithmThought._01_DoublePointer._633_SumOfSquare;

/*
 * 平方数之和
 *
 * 题目描述：
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a 的平方加 b 的平方等于 c。
 *
 * 思路：
 * 1. 还是使用双指针的方式，只不过这里的右指针为 sqrt(c)；
 * 2. 时间复杂度为 O(sqrt(c))，空间复杂度为 O(1)。
 */
public class Solution {

    public static boolean judgeSquareSum(int c) {
        if (c < 0) {
            return false;
        }

        int left = 0;
        int right = (int)Math.sqrt(c);
        while (left <= right) {
            int squareSum = left * left + right * right;
            if (squareSum == c) {
                return true;
            } else if (squareSum < c){
                left++;
            } else {
                right++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Solution.judgeSquareSum(4));
        System.out.println(Solution.judgeSquareSum(5));
    }
}
