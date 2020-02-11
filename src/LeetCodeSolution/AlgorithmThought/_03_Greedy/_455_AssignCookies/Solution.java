package LeetCodeSolution.AlgorithmThought._03_Greedy._455_AssignCookies;

import java.util.Arrays;

/*
 * 分配饼干
 *
 * 题目描述：
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。
 * 但是，每个孩子最多只能给一块饼干。对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干的最小尺寸；
 * 并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
 * 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 * 思路：
 * 1. 给孩子和饼干都进行排序；
 * 2. 维护两个指针，如果当前尺寸的饼干满足当前孩子的胃口，则两个指针一起往后移动；
 * 3. 如果如果当前尺寸的饼干不能满足当前孩子的胃口，则只需要饼干往后移动一下，再进行判断即可。
 */
public class Solution {

    public static int findContentChildren(int[] g, int[] s) {
        if (g == null || s == null || g.length == 0 || s.length == 0) {
            return 0;
        }

        Arrays.sort(g);
        Arrays.sort(s);

        int gi = 0;
        int sj = 0;
        // 这个地方和判断是不是子序列类似
        while (gi < g.length && sj < s.length) {
            if (g[gi] <= s[sj]) {
                gi++;
            }
            sj++;
        }
        return gi;
    }

    public static void main(String[] args) {
        int[] g = {1, 2, 3};
        int[] s = {1, 1};
        System.out.println(findContentChildren(g, s));
    }
}
