package LeetCodeSolution.DataStructure._02_String._32_LongestValidParentheses;

import java.util.Stack;

/*
 * 最长有效括号
 *
 * 题目描述：
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 思路一：
 * 1. 暴力，类似于双指针，使用一个指针指向当前字符，另一个指针指向下一个字符，判断两个指针之间的字符串是否是有效的括号；
 * 2. 在进行判断的时候，就需要用到之前题目中的验证是否是有效括号的方法。
 *
 * 思路二：
 * 1. 直接使用栈；
 * 2. 只要遇到左括号就入栈，而如果遇到右括号，则说明当前的右括号和栈顶的左括号是匹配的，那么栈顶括号出栈；
 * 3. 用当前右括号的下标减去新的栈顶括号的下标，注意这里为什么需要将栈顶元素弹出后，再减去新的栈顶元素的下标？
 * 4. 由于之前栈顶括号的出栈，导致新的栈顶括号还没有匹配成功，同时之前栈顶元素的出栈已经和右括号匹配成功了。
 *
 * 思路三：
 * 1. dp；
 * 2. dp[i] 表示以下标 i 结尾的最长有效字符串的长度，那么以 '(' 结尾的当前字符我们不考虑，因为不可能构成合法括号；
 * 3. 如果 s[i]==')'：
 *      如果 s[i-1]=='('，也就是形如 ".....()" 的形式，则可以得到 dp[i]=dp[i-2]+2，也就是之前的最大长度再加上这两个括号的长度；
 *      如果 s[i-1]==')'，也就是形如 ".....))" 的形式，则可以得到：如果 s[i-dp[i-1]-1]=='('，则有 dp[i]=dp[i-1]+2+dp[i-dp[i-1]-2]；
 *          也就是说，如果倒数第二个 ')' 是有效字符串的一部分（记为 subs），我们此时需要判断 subs 前一个符号是不是 '('；
 *          如果是 '('，则需要用 subs 的长度（dp[i-1]）加上 2 去更新 dp[i]；
 *          此外，也需要将 subs 前面有效字符串的长度加上，即 dp[i-dp[i-1]-2]。
 * 4. 对于 i-2 越界的情况，直接记为 0 即可。
 */
public class Solution {
    public int longestValidParentheses1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == ')') {
                continue;
            }
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == '(' || (j - i + 1) % 2 == 1) {
                    continue;
                }
                if (isValid(s.substring(i, j + 1))) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }

    // 该函数的功能是验证字符串 s 是不是有效的括号，
    // 可以单独作为一道题
    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (Character c : s.toCharArray()) {
            if (stack.size() == 0) {
                stack.push(c);
            } else if (isSame(stack.peek(), c)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.size() == 0;
    }

    private boolean isSame(char c1, char c2) {
        return c1 == '(' && c2 == ')';
    }


    // 直接使用栈
    public int longestValidParentheses2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
                // 跳过，考察下一个字符
                continue;
            }
            // 走到这里说明遇到右括号了，
            // 那么让栈顶出栈
            stack.pop();
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                // 这里开始计算有效括号的长度
                res = Math.max(res, i - stack.peek());
            }
        }
        return res;
    }

    // DP
    public int longestValidParentheses3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        s = " " + s;
        int res = 0;
        int[] dp = new int[s.length()];
        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = dp[i - 2] + 2;
                } else if (s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2];
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = ")()())";
        String s2 = "(()";

        System.out.println(solution.longestValidParentheses1(s1));
        System.out.println(solution.longestValidParentheses1(s2));
        System.out.println("--");

        System.out.println(solution.longestValidParentheses2(s1));
        System.out.println(solution.longestValidParentheses2(s2));
        System.out.println("--");

        System.out.println(solution.longestValidParentheses3(s1));
        System.out.println(solution.longestValidParentheses3(s2));
    }
}
