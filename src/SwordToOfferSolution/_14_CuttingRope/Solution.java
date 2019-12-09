package SwordToOfferSolution._14_CuttingRope;

/*
 * 剪绳子
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
