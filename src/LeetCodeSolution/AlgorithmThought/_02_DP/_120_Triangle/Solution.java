package LeetCodeSolution.AlgorithmThought._02_DP._120_Triangle;

import java.util.Arrays;
import java.util.List;

/*
 * 三角形最小路径和
 *
 * 题目描述：
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点在这里指的是下标与上一层结点下标相同或者等于上一层结点下标 + 1 的两个结点。
 *
 * 思路：
 * 1. 使用 DP 思想，dp[i][j] 表示从点 (i, j) 到底边的最小路径和；
 * 2. 因此状态转移方程为：dp[i][j]=min(dp[i+1][i], dp[i+1][j+1])+triangle[i][j]；
 * 3. 根据题意可知，这里将最小路径和转换为与该点相邻两点到底边的最小路径和中的最小值，再加上该点本身的值；
 * 4. 当然，还可以进行空间上的优化；
 * 5. 上面定义了 n×n 行的数组，但是实际上，当前的位置 i，只与下一行的 [i+1,j] 和 [i+1,j+1] 有关；
 * 6. 因此，可以将 i 维度去掉即可。
 */
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null) {
            return 0;
        }

        int n = triangle.size();
        int[][] dp = new int[n + 1][n + 1];
        // 从三角形的最后一行开始递推
        // 自低向上
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle == null) {
            return 0;
        }

        int n = triangle.size();
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> list1 = Arrays.asList(2);
        List<Integer> list2 = Arrays.asList(3, 4);
        List<Integer> list3 = Arrays.asList(6, 5, 7);
        List<Integer> list4 = Arrays.asList(4, 1, 8, 3);
        List<List<Integer>> triangle = Arrays.asList(list1, list2, list3, list4);

        System.out.println(solution.minimumTotal(triangle));
        System.out.println(solution.minimumTotal2(triangle));
    }
}
