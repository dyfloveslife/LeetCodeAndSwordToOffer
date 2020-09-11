package LeetCodeSolution.DataStructure._02_String._227_BasicCalculatorII;

import java.util.Stack;

/*
 * 基本计算器 Ⅱ
 *
 * 题目描述：
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式仅包含非负整数、+、 - 、*、/ 四种运算符和空格。
 * 整数除法仅保留整数部分。
 *
 * 思路：
 * 1. 整体的思路是使用栈；
 * 2. 首先遍历字符串，如果当前是数字的话，那么直接更新 num；
 * 3. 如果是字符（加减乘除）的话，则需要根据具体的符号进行计算。
 */
public class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        s = s.trim();
        char sign = '+';
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 如果是数字，则更新 num
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            // 如果是加减乘除符号，则根据不同的符号进行入栈操作
            if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(num * stack.pop());
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                // 将 sign 设置为当前符号 c
                sign = c;
                num = 0;
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "3+2*2";
        String s2 = " 3/2 ";
        String s3 = " 3+5 / 2 ";

        System.out.println(solution.calculate(s1));
        System.out.println(solution.calculate(s2));
        System.out.println(solution.calculate(s3));
    }
}
