package SwordToOfferSolution._14_CuttingRope;

/*
 * 剪绳子
 *
 * 题目描述：
 * 给你一根长度为 n 绳子，请把绳子剪成 m 段（m、n 都是整数，n>1 并且 m≥1）。
 * 每段的绳子的长度记为 k[0]、k[1]、……、k[m]。则 k[0]*k[1]*…*k[m] 可能的最大乘积是多少？
 * 例如当绳子的长度是 8 时，我们把它剪成长度分别为 2、3、3 的三段，此时得到最大的乘积 18。
 *
 */
public class Solution {
    /*
     * 动态规划
     * 0  1  2  3  4  5
     * 0  1  2  3 (4) (6)
     */
    public static int cuttingRopeSolution1(int length) {
        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }

        int[] arr = new int[length + 1];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 2;
        int res;
        for (int i = 4; i <= length; i++) {
            int max = 0;
            for (int j = 1; j <= i / 2; j++) {
                int num = arr[j] * arr[i - j];
                if (max < num) {
                    max = num;
                }
                arr[i] = max;
            }
        }
        res = arr[length];
        return res;
    }

    // 贪婪
    public static int cuttingRopeSolution2(int length) {
        if (length <= 3) {
            return 1 * (length - 1);
        }
        int res = 1;
        if (length % 3 == 1) {
            res = 4;
            length -= 4;
        } else if (length % 3 == 2) {
            res = 2;
            length -= 2;
        }
        while (length != 0) {
            res *= 3;
            length -= 3;
        }
        return res;
    }

    public static void main(String[] args) {
        int i = cuttingRopeSolution2(8);
        System.out.println(i);
    }
}
