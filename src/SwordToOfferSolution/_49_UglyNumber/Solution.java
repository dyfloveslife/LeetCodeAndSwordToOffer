package SwordToOfferSolution._49_UglyNumber;

import java.util.ArrayList;

/*
 * 丑数
 *
 * 题目描述：
 * 我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 1500 个丑数。
 * 例如 6、8 都是丑数，但 14 不是，因为它包含因子 7。习惯上我们把 1 当做第一个丑数。
 *
 * 思路 1 用于判断一个数是否是丑数：
 * 1. 根据丑数的定义，只包含因子 2、3 和 5，所以一个丑数就可以被定义为 2^i * 3^j * 5^k，即每个对应次方的乘积；
 *    注意底数都是素数（质数）
 * 2. while (num % factor == 0) num /= factor;
 *    check num == 1
 *    即每次将 num 除以因子，最后检查 num 是否等于 1，如果等于 1，则是丑数。
 * 3. O(log2 n + log3 n + log5 n)
 * 4. O(1)
 *
 * 思路 2：
 * 1. 比较用于乘 2 的最小的数、乘 3 的最小的数、乘 5 的最小的数即可；
 * 2. 每次从队头中弹出最小的数，将该数加入到丑数数组中，然后用该数分别乘以 2、3、5，并将结果放入对应的队列中；
 * 3. 如果队头的数相同，则都弹出。
 *
 * 丑数数组：1、2、3、4、5
 * 乘 2 的队列：6、8、10
 * 乘 3 的队列：6、9、12、15
 * 乘 5 的队列：10、15、20、25
 *             队头-------队尾
 * https://www.nowcoder.com/questionTerminal/6aa9e04fc3794f68acf8778237ba065b?f=discussion
 */
public class Solution {

    // 判断一个数是否是丑数
    public static boolean isUgly(int num) {
        if (num == 0) {
            return false;
        }

        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (num % factor == 0) {
                num /= factor;
            }
        }
        return num == 1;
    }

    public static int uglyNumber(int index) {
        if (index < 1) {
            return 0;
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        // 用于记录丑数的位置
        int i2 = 0, i3 = 0, i5 = 0;
        while (list.size() < index) {
            int m2 = list.get(i2) * 2;
            int m3 = list.get(i3) * 3;
            int m5 = list.get(i5) * 5;
            // 由于三个数都有可能是丑数，所以要取三者中最小的
            int min = Math.min(m2, Math.min(m3, m5));
            list.add(min);
            if (min == m2) {
                i2++;
            }
            if (min == m3) {
                i3++;
            }
            if (min == m5) {
                i5++;
            }
        }
        return list.get(list.size() - 1);
    }

    public static int uglyNumber2(int n) {
        if (n < 7) {
            return n;
        }

        int[] arr = new int[n];
        int i2 = 0, i3 = 0, i5 = 0;
        arr[0] = 1;

        for (int i = 1; i < n; i++) {
            int m2 = arr[i2] * 2;
            int m3 = arr[i3] * 3;
            int m5 = arr[i5] * 5;
            arr[i] = Math.min(m2, Math.min(m3, m5));
            if (arr[i] == m2) {
                i2++;
            }
            if (arr[i] == m3) {
                i3++;
            }
            if (arr[i] == m5) {
                i5++;
            }
        }
        return arr[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(uglyNumber(7));
        System.out.println(isUgly(6));
        System.out.println(isUgly(14));
    }
}
