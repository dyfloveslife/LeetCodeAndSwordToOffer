package LeetCodeSolution.AlgorithmThought._05_DivideAndConquer._241_DifferentWaysToAddParentheses;

import java.util.ArrayList;
import java.util.List;

/*
 * 为运算表达式设计优先级
 *
 * 题目描述：
 * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。
 * 你需要给出所有可能的组合的结果。有效的运算符号包含 + 号、- 号以及 * 号。
 *
 * 思路：
 * 1. 按照运算符将问题分解成子问题，分别计算后，利用分隔符再进行合并‘
 * 2. 比如 2*3-4*5，先分成 2 和 3-4*5 两部分，中间的分隔符就是 *，此时的解分别为 [2] 和 [-5, -17]；
 *        [-5, -17] 是 3-4*5 进行分治的结果，即可分成 (3-4)*5 和 3-(4*5)；
 * 3. 然后还可以分成 2*3 和 4*5 两部分，此时的解分别为 [6] 和 [20]；
 * 4. 然后还可以分成 2*3-4 和 5 两部分，此时的解分别为 [2, -2] 和 [5]；
 *        [2, -2] 是 2*3-4 进行分治的结果，即可分成 (2*3)-4 和 2*(3-4)
 */
public class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        if (!input.contains("+") && !input.contains("-") && !input.contains("*")) {
            res.add(Integer.valueOf(input));
            return res;
        }

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                // 分治
                // 由于 i 是 + 号、- 号以及 * 号的情况，所以左右进行分治的时候，
                // 左部分的区间是 [0, i-1]，右部分的区间是 [i+1, 字符串末尾]
                // 也就是将 i 位置的字符给空出来
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                for (Integer l : left) {
                    for (Integer r : right) {
                        if (input.charAt(i) == '+') {
                            res.add(l + r);
                        } else if (input.charAt(i) == '-') {
                            res.add(l - r);
                        } else if (input.charAt(i) == '*') {
                            res.add(l * r);
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "2-1-1";
        String s2 = "2*3-4*5";
        System.out.println(solution.diffWaysToCompute(s1));
        System.out.println(solution.diffWaysToCompute(s2));
    }
}