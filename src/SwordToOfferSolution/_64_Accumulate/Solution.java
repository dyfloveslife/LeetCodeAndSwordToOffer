package SwordToOfferSolution._64_Accumulate;

/*
 * 求 1+2+…+n
 *
 * 题目描述：
 * 求 1+2+…+n，要求不能使用乘除法、for、while、if、else、switch、case 等关键字及条件判断语句（A?B:C）。
 *
 * 思路：
 * 1. 对于常见的运算符：
 *     单目运算符：++ 和 --；
 *     双目运算符：+ 和 -；
 *     移位运算符：<< 和 >>;
 *     关系运算符： < 和 >;
 *     逻辑运算符：&&、||、|、^、=
 * 2. 由于关键字的限制，这里使用短路运算符 &&;
 * 3. 如果前面的条件为假，则整个表达式的结果就是假，后面的条件就不进行判断了;
 * 4. n 直到 0 就返回结果。
 * 5. 关于短路运算：
 *    例如 if (A && B)：
 *         如果 A 为 false，则不会执行并判断 B，即 && 短路；
 *    例如 if (A || B)：
 *         如果 A 为 true，则不会执行并判断 B，即 || 短路。
 * 6. 因此，如果想要当 n 等于 1 时终止递归的话，则需要使用 n > 1 && sunNums(n - 1)；
 * 7. 即当 n 等于 1 时，n > 1 不成立，此时短路，从而终止 sunNums(n - 1) 递归。
 */
public class Solution {
    int res = 0;

    public int sumNums(int n) {
        boolean flag = (n > 1) && sumNums(n - 1) > 0;
        res += n;
        return res;
    }

    public int sumNums2(int n) {
        boolean flag = (n > 1) && (n += sumNums2(n - 1)) > 0;
        return n;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.sumNums(9));
        System.out.println(solution.sumNums2(9));
    }
}