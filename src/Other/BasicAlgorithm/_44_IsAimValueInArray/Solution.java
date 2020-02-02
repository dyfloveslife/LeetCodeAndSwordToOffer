package Other.BasicAlgorithm._44_IsAimValueInArray;

/*
 * 数组中的数累加起来等于目标值
 *
 * 题目描述：
 * 给定一个数组 arr 和一个整数 aim，如果可以任意选择 arr 中的数字，能不能累加得到 aim。
 * 若能，则返回 true，否则返回 false。
 * arr 中的数以及 aim 都是正数。
 *
 * 思路：
 * 1. 如图所示：https://i.loli.net/2020/02/02/nCPM2vyzkpSh5xV.png
 * 2. 用 f(0, 0) 表示在 arr[0] 位置与之前数字累加得到的和；
 * 3. 则 f(0, 0) 在 0 位置可以选择 3 或者不选择 3；
 * 4. 如果选择 3 则变成 f(1, 3)，如果不选择 3 则变成 f(1, 0)；
 * 5. 重复此步骤，直到最后的叶节点，如果叶节点中有等于目标值 aim 的话，则返回 true。
 */
public class Solution {

    /**
     * 暴力递归
     * 属于无后效性问题，可变参数只有 i 和 sum
     * DP 表：
     * 横坐标从 0~sum，sum 不会超过数组中所有数字加起来的和
     * 纵坐标是 0~i，数组中每个数的位置
     *
     * @param arr 给定的数组
     * @param i   数组中的当前位置
     * @param sum 当前的和
     * @param aim 给定的目标值
     * @return
     */
    public static boolean isAimValueInArray1(int[] arr, int i, int sum, int aim) {
        // 当 i 来到了数组中的最后一个位置，如果当前的累加和 sum 等于 aim 的话，则返回 true
        if (i == arr.length) {
            return sum == aim;
        }
        // 下一个位置的数对于上一个位置的数的选择有两种：选或者不选
        // 只要一旦存在等于 aim 的数，则返回 true
        return isAimValueInArray1(arr, i + 1, sum + arr[i], aim) || isAimValueInArray1(arr, i + 1, sum, aim);
    }

    /**
     * DP 版本
     *
     * @param arr
     * @param aim
     * @return
     */
    public static boolean isAimValueInArray2(int[] arr, int aim) {
        boolean[][] dp = new boolean[arr.length + 1][aim + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][aim] = true;
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = aim - 1; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j];
                if (j + arr[i] <= aim) {
                    dp[i][j] = dp[i][j] || dp[i + 1][j + arr[i]];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 8};
        int aim = 8;
        System.out.println(isAimValueInArray1(arr, 0, 0, aim));
        System.out.println(isAimValueInArray2(arr, aim));
    }
}
