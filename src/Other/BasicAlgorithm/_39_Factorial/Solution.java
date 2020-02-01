package Other.BasicAlgorithm._39_Factorial;

/*
 * 求 n 的阶乘
 */
public class Solution {

    // 从需要依赖到不需要依赖
    public static long getFactorial1(int n) {
        // base case
        if (n == 1) {
            return 1L;
        }

        return (long) n * getFactorial1(n - 1);
    }

    // 我知道怎么计算，直接计算就好了
    // 从不需要依赖的问题到依赖的问题
    // 即 1 * 2 * 3 ... * n，一开始的值不被依赖，则后面的值需要依赖前面的值
    public static long getFactorial2(int n) {
        long res = 1L;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println(getFactorial1(n));
        System.out.println(getFactorial2(n));
    }
}
