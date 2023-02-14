package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._118_PascalsTriangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 杨辉三角
 *
 * 题目描述：
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * 思路：
 * 1. 第 n 行的第 i 个数等于 n - 1 行的第 i - 1 个数加第 n - 1 行的第 i 个数；
 * 2. 时间复杂度为 O(numsRows ^ 2)，不考虑返回值所占空间的话，空间复杂度为 O(1)。
 */
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if (numRows < 1) {
            return ans;
        }

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            // 第 n 行有 n + 1 个数字（n 从 0 开始）
            // 所以第 i 行的下标 j 最多来到 i
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
                }
            }
            ans.add(row);
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(Arrays.toString(solution.generate(5).toArray())); // [[1], [1, 1], [1, 2, 1], [1, 3, 3, 1], [1, 4, 6, 4, 1]]
        System.out.println(Arrays.toString(solution.generate(1).toArray())); // [[1]]
    }
}
