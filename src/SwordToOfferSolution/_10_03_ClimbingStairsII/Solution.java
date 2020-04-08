package SwordToOfferSolution._10_03_ClimbingStairsII;

/*
 * 爬楼梯Ⅱ
 *
 * 题目描述：
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级……它也可以跳上 n 级。
 * 求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * 思路：
 * 1. 对于 n 级台阶，第一步有 n 种跳法，即跳 1 级、跳 2 级、跳 3 级、...、跳 n 级；
 * 2. 如果跳 1 级，则还剩下 n-1 级跳法，即 f(n-1)；
 * 3. 如果跳 2 级，则还剩下 n-2 级跳法，即 f(n-2);
 * 4. 如果跳 3 级，则还剩下 n-3 级跳法，即 f(n-3);
 * 5. ...
 * 6. f(n) = f(n-1) + f(n-2) + ... + f(1)；
 * 7. 又因为 f(n-1) = f(n-2) + f(n-3) + ... + f(1)；
 * 8. 两式相减，得到 f(n) = 2*f(n-1)。
 */
public class Solution {
    public int climbingStairsII(int n) {
        int res = 1;
        while (n-- > 1) {
            res *= 2;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.climbingStairsII(6));
    }
}
