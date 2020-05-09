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
 * 0. 整体思路就是使用二分法来猜 x 开根号的结果，高了就往低了猜，低了就往高了猜，范围越来越小；
 * 1. 其次，一个数 x 的平方根最多不会超过 x 的一半，例如 8 的平方根是 2.8，而 8 的一半是 4；
 * 2. 由于一个数 x 的平方根 sqrt（被开方）一定存在于 0~x 之内；
 * 3. 这里使用 sqrt == x /middle 防止溢出，而不是使用 middle*middle >x；
 * 5. 注意 8 的平方根是 2.8，则需要将 0.8 舍去，最后返回 2。
 */
public class Solution {

    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }

        int left = 1;
        int right = x;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            int sqrt = x / middle;
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
        Solution solution = new Solution();

        System.out.println(solution.mySqrt(8));
    }
}
