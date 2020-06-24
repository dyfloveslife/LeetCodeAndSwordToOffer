package LeetCodeSolution.DataStructure._05_StackAndQueue._20_ValidParentheses;

import java.util.Stack;

/*
 * 有效的括号
 *
 * 题目描述：
 * 给定一个只包括 (、)、{、}、[、] 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 思路：
 * 1. 需要注意的一点的是，每对括号之间不能包含其它括号。
 */
public class Solution {

    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }

        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            // 如果栈中没有东西的话，则说明当前处于初始状态，因此需要将字符 c 入栈
            if (stack.size() == 0) {
                stack.push(c);
                // 能够走到下面的分支，说明栈中已经有东西了，
                // 那么此时需要将当前字符 c 与栈顶字符进行比较，
                // 看看是否匹配，如果匹配，则说明找到了一对，那么就弹出
                // 这里需要注意参数的顺序，一定是先拿栈顶的字符去与 c 比较，
                // 因为左字符需要先入栈，这里与 isSame() 方法中定义的顺序有关
            } else if (isSame(stack.peek(), c)) {
                stack.pop();
                // 能够到这里，说明当前字符 c 与栈顶字符不匹配，因此需要将其入栈
            } else {
                stack.push(c);
            }
        }
        return stack.size() == 0;
    }

    private boolean isSame(char c1, char c2) {
        return (c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}') || (c1 == '[' && c2 == ']');
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "([)]";
        String s2 = "()[]{}";

        System.out.println(solution.isValid(s1));
        System.out.println(solution.isValid(s2));
    }
}
