package LeetCodeSolution.AlgorithmThought._06_Search._22_GenerateParentheses;

import java.util.ArrayList;
import java.util.List;

/*
 * 生成括号
 *
 * 题目描述：
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 思路：
 * 1. DFS + 剪枝；
 * 2. 这里有两种方式，可以使用“减法”和“加法”：
 *    2.1) “减法”：假设初始的时候总共有 2 对括号，已经收集到的括号为 0，
 *         在每次收集的过程中从已有的括号中不断减去左括号或右括号，直到左右括号剩下为 0 即可；
 *    2.2) “加法”：假设初始的时候没有括号，已经收集到的括号为 0，目标括号数为 2，
 *         在每次收集的过程中不断的收集左右括号，直到收集到左右括号数都为 2 的时候即可。
 */
public class Solution {
    // 做减法
    public List<String> generateParenthesis1(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        dfs1(res, "", n, n);
        return res;
    }

    /**
     * @param res    结果集
     * @param curStr 当前递归得到的结果
     * @param left   由于是做减法，则 left  表示左括号还有几个可以使用，即左括号的剩余数量
     * @param right  由于是做减法，则 right 表示右括号还有几个可以使用，即右括号的剩余数量
     */
    public void dfs1(List<String> res, String curStr, int left, int right) {
        // 如果左右括号的数量均为 0 了，则说明可以收集结果了
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }

        // 剪枝，当左括号的剩余个数大于右括号的剩余个数时，进行剪枝
        // 假设括号对数为 2，给定一个例子 ())，则左括号的剩余数量是 1，右括号的剩余数量是 0，
        // 则此时需要剪枝
        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs1(res, curStr + "(", left - 1, right);
        }
        if (right > 0) {
            dfs1(res, curStr + ")", left, right - 1);
        }
    }


    // 做加法
    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        dfs2(res, n, "", 0, 0);
        return res;
    }

    /**
     * @param res    结果集
     * @param n      左右括号一共需要用几个
     * @param curStr 当前递归得到的结果
     * @param left   左括号已经使用的数量
     * @param right  右括号已经使用的数量
     */
    public void dfs2(List<String> res, int n, String curStr, int left, int right) {
        if (left == n && right == n) {
            res.add(curStr);
            return;
        }

        // 剪枝，假设括号对数为 2，给定一个例子 ())，左括号已经使用了 1 个，右括号已经使用了 2 个，
        // 此时需要进行剪枝，因为这种不符合括号匹配的情况。
        if (left < right) {
            return;
        }

        if (left < n) {
            dfs2(res, n, curStr + "(", left + 1, right);
        }
        if (right < n) {
            dfs2(res, n, curStr + ")", left, right + 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.generateParenthesis1(3));
        System.out.println(solution.generateParenthesis2(3));
    }
}
