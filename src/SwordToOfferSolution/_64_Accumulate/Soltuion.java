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
 * 3. 如果前面的条件为假，则真个表达式的结果就是假，后面的条件就不进行判断了;
 * 4. n 直到 0 就返回结果。
 */
public class Soltuion {
    public int sum_Solution(int n) {
        int sum = n;
        boolean flag = (sum > 0) && ((sum += sum_Solution(--n)) > 0);
        return sum;
    }
}
