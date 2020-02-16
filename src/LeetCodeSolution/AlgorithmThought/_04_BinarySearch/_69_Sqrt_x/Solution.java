package LeetCodeSolution.AlgorithmThought._04_BinarySearch._69_Sqrt_x;

/*
 * x 的平方根，即求开方
 *
 * 题目描述：
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 思路：
 * 1. 由于一个数 x 的平方根 sqrt（被开方）一定存在于 0~x 之内，并且 sqrt == x /sqrt；
 * 2. 可以使用二分法在 0~x 之间查找；
 * 3. 注意 8 的平方根是 2.8，则需要将 0.8 舍去，最后返回 2。
 */
public class Solution {
    public static int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }

        int left = 1;
        int right = x;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            int sqrt = x / middle;
            // 首先拿被开方的 sqrt 与 middle 比较
            if (sqrt == middle) {
                return sqrt;
            } else if (sqrt < middle) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(8));
    }
}
