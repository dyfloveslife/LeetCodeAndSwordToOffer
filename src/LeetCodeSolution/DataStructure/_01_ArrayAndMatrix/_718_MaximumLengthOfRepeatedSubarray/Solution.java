package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._718_MaximumLengthOfRepeatedSubarray;

/*
 * 最长重复子数组
 *
 * 题目描述：
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 *
 * 思路：
 * 1. 定义 dp[i][j] 表示以元素 A[i-1] 与 B[j-1] 结尾的公共最长子数组；
 * 2. 如果 A[i-1]==B[j-1]，则 dp[i][j]=dp[i-1][j-1]+1；
 * 3. 如果 A[i-1]!=B[j-1]，则 dp[i][j]=0；
 * 4. 最后，如果构成了公共最长子数组，则记得维护它的最大长度，最终返回即可。
 */
public class Solution {
    public int findLength(int[] A, int[] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0) {
            return 0;
        }

        int res = 0;
        int[][] dp = new int[A.length + 1][B.length + 1];

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {1, 2, 3, 2, 1};
        int[] B = {3, 2, 1, 4, 7};

        System.out.println(solution.findLength(A, B));
    }
}
