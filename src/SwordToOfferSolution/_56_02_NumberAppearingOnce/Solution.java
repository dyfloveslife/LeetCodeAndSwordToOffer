package SwordToOfferSolution._56_02_NumberAppearingOnce;

/*
 * 数组中唯一只出现一次的数字
 *
 * 题目描述：
 * 在一个数组中除了一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 *
 * 思路:
 * 1. 和上题不同的是，三个相同的数字的异或结果还是该数字本身；
 * 2. 这里采用位运算的思路，如果一个数字出现了 3 次，则它的二进制表示的每一位也出现 3 次；
 * 3. 如果把所有出现 3 次的数字的二进制表示的每一位都分别加起来，那么每一位的和都可以被 3 整除；
 * 4. 如果不能被 3 整除的话，则说明那个只出现 1 次的数贡献出了 1；
 * 5. 将所有不是 3 的倍数的列写成 1，其它列写 0。
 */
public class Solution {

    public static int findNumberApperingOnce1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }

        int ans = 0;
        // 考虑每一位，每次都将当前数字的二进制表示形式向右移动 i 位
        for (int i = 0; i < 32; i++) {
            int count = 0;
            // 考虑每个数
            for (int j = 0; j < arr.length; j++) {
                // 判断当前位是不是 1
                if ((arr[j] >> i & 1) == 1) {
                    count++;
                }
            }
            // 判断每位 1 的个数是不是 3 的倍数，<< 的优先级高于 |
            // 先将 1 向左移动 i 位，用来控制计算是哪一位的 1 的个数
            // 然后再与 ans 进行或运算
            // 同时也是为了得到只出现 1 次的那个数
            if (count % 3 != 0) {
                ans = ans | 1 << i;
            }
        }
        return ans;
    }

    public static int findNumberApperingOnce2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }

        int[] bitSum = new int[32];
        for (int i = 0; i < arr.length; i++) {
            int bitMask = 1;
            for (int j = 31; j >= 0; j--) {
                int bit = arr[i] & bitMask;
                if (bit != 0) {
                    bitSum[j] += 1;
                }
                bitMask <<= 1;
            }
        }

        int res = 0;
        for (int k = 0; k < 32; k++) {
            res <<= 1;
            res += bitSum[k] % 3;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findNumberApperingOnce1(new int[]{1, 2, 6, 1, 1, 2, 2, 3, 3, 3}));
    }
}
