package SwordToOfferSolution._65_AddTwoNumbers;

/*
 * 不用加减乘除做加法
 *
 * 题目描述：
 * 写一个函数，求两个整数之和，要求在函数体内不得使用＋、－、×、÷ 四则运算符号。
 *
 * 思路：
 * 1. 先以十进制数为例，例如 17+05=？。这里分三步走完成计算：
 *    1) 将各位（注意是每一位，而不是个位）进行相加而不进位，即 1+0=1，7+5=2，从而得到 12；
 *    2) 然后记下来进位，由于 7+5 中有进位为 10；
 *    3) 最后将前两步的数加起来即可，即 12+10=22。
 * 2. 将十进制转化为二进制，还是上面的例子： 10001+00101=？，也是分三步走：
 *    1) 将各位进行相加而不进位，得到 10100；
 *    2) 然后记下来进位，该例子中只在最后一位有进位，即 10；
 *    3) 将两步结果相加即可，即 10100+10=10110，即为十进制数 22。
 * 3. 对于二进制的加法来说，由于 0+0=0，1+1=0，0+1=1，1+0=1，所以这相当于进行了 异或 操作；
 * 4. 对于 0+0=0，0+1=1，1+0=1 来说都不会产生进位，而产生进位的只有 1+1=0，在这里可以将两个数字先做 与 运算，然后再向左移动一位；
 * 5. 最后将这两步得到的结果进行相加即可，直到不产生进位为止。
 *
 * 不使用新变量，交换两个变量的值：
 *  1）a = a + b;
 *     b = a - b;
 *     a = a - b;
 *
 *  2）a = a ^ b;
 *     b = a ^ b;
 *     a = a ^ b;
 */
public class Solution {
    public static int add(int num1, int num2) {
        while (num2 != 0) {
            // 先进行异或操作，相当于不带进位的加法
            int sum = num1 ^ num2;
            // 进行与运算，再左移一位，相当于求出进位
            int carry = (num1 & num2) << 1;
            num1 = sum;
            num2 = carry;
        }
        return num1;
    }

    // 递归版
    public static int add2(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }

        return add2(num1 ^ num2, (num1 & num2) << 1);
    }

    public static void main(String[] args) {
        System.out.println(add(5, 17));
        System.out.println(add2(5, 17));
    }
}
