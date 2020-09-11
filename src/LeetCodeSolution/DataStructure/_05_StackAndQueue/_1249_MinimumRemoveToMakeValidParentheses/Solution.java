package LeetCodeSolution.DataStructure._05_StackAndQueue._1249_MinimumRemoveToMakeValidParentheses;

import java.util.Stack;

/*
 * 移除无效的括号
 *
 * 题目描述：
 * 给你一个由 (、) 和小写字母组成的字符串 s。
 * 你需要从字符串中删除最少数目的 ( 或者 )（可以删除任意位置的括号)，使得剩下的「括号字符串」有效。请返回任意一个合法字符串。
 * 有效「括号字符串」应当符合以下任意一条要求：
 * ① 空字符串或只包含小写字母的字符串；
 * ② 可以被写作 AB（A 连接 B）的字符串，其中 A 和 B 都是有效「括号字符串」；
 * ③ 可以被写作 (A) 的字符串，其中 A 是一个有效的「括号字符串」。
 *
 * 思路：
 * 1. 使用栈，栈中只存放括号的索引，不考虑其它字符或字母；
 * 2. 使用一个 boolean 类型的数组，统计当前位置是否是一个有效的括号；
 * 3. 遍历给定的字符串，如果当前字符是左括号，那么将它的索引入栈，并将 boolean 数组对应的位置置为 true；
 * 4. 如果当前字符是右括号，那么需要判断栈是否为空：
 *    如果栈为空，则说明没有与之对应的左括号，因此将当前的右括号对应的 boolean 数组对应的位置置为 true；
 *    如果栈不为空，则弹出栈顶元素，并将该弹出的元素在 boolean 数组中相应的位置置为 false；
 * 5. 最后再次遍历字符串，将不是 false 的位置添加到结果集中即可。
 */
public class Solution {
    public String minRemoveToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        Stack<Integer> stack = new Stack<>();
        boolean[] isValid = new boolean[s.length()];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
                isValid[i] = true;
            }
            if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    isValid[i] = true;
                } else {
                    int index = stack.pop();
                    isValid[index] = false;
                }
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (!isValid[i]) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str1 = "lee(t(c)o)de)";
        String str2 = "a)b(c)d";
        String str3 = "))((";

        System.out.println(solution.minRemoveToMakeValid(str1));
        System.out.println(solution.minRemoveToMakeValid(str2));
        System.out.println(solution.minRemoveToMakeValid(str3)); // ""
    }
}
