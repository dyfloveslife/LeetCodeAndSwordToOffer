package LeetCodeSolution.DataStructure._02_String._1541_MinimumInsertionsToBalanceAParenthesesString;

/*
 * 平衡括号字符串的最少插入次数
 *
 * 题目描述：
 * 给你一个括号字符串 s ，它只包含字符 '(' 和 ')' 。一个括号字符串被称为平衡的当它满足：
 * 任何左括号 '(' 必须对应两个连续的右括号 '))' 。
 * 左括号 '(' 必须在对应的连续两个右括号 '))' 之前。
 * 比方说 "())"， "())(())))" 和 "(())())))" 都是平衡的， ")()"， "()))" 和 "(()))" 都是不平衡的。
 * 你可以在任意位置插入字符 '(' 和 ')' 使字符串平衡。
 * 请你返回让 s 平衡的最少插入次数。
 *
 * 思路：
 * 1. 直接模拟即可，但也有一些技巧；
 * 2. 具体看代码中的注释。
 */
public class Solution {
    public int minInsertions(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 当前已经遍历到的左括号的个数
        int leftCount = 0;
        // 需要插入的括号个数
        int insert = 0;
        int index = 0;
        while (index < s.length()) {
            char c = s.charAt(index);
            // 如果遇到左括号，则左括号的个数加 1，同时下标加 1
            if (c == '(') {
                leftCount++;
                index++;
            // 如果遇到右括号
            } else {
                // 首先判断当前的右括号是否需要与前面的左括号进行匹配，
                // 如果存在左括号，则说明可以匹配，则左括号数量减 1，
                // 如果不存在左括号，则说明不能匹配，则需要插入一个左括号
                if (leftCount > 0) {
                    leftCount--;
                } else {
                    insert++;
                }
                // 如果当前位置与它相邻的后面的位置都是连续的右括号的话，
                // 那么就将下标加 2，来到下一个未知的位置
                if (index < s.length() - 1 && s.charAt(index + 1) == ')') {
                    index += 2;
                // 如果当前位置为右括号，而它后面相邻的位置不是右括号的话，
                // 则需要在当前位置的后面插入一个右括号，这样才能构成两个连续的右括号
                } else {
                    insert++;
                    index++;
                }
            }
        }
        insert += leftCount * 2;
        return insert;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "(()))";
        String s2 = "())";
        String s3 = "))())(";

        System.out.println(solution.minInsertions(s1));
        System.out.println(solution.minInsertions(s2));
        System.out.println(solution.minInsertions(s3));
    }
}
