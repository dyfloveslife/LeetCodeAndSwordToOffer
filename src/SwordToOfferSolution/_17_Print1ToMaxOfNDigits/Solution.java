package SwordToOfferSolution._17_Print1ToMaxOfNDigits;

import java.util.Arrays;

/*
 * 打印从 1 到最大的 n 位数
 *
 * 题目描述：
 * 输入数字 n，按顺序打印出从 1 最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数即 999。
 *
 * 思路：
 * 将 1~pow(10, n)-1 问题转化为求 0~9 在 n 个位置上的全排列。
 */
public class Solution {

    public static int[] printNumbers(int n) {
        if (n < 1) {
            return null;
        }

        int max = (int) Math.pow(10, n);
        int[] res = new int[max - 1];
        for (int i = 1; i <= max - 1; i++) {
            res[i - 1] = i;
        }
        return res;
    }

    // 方法一
    private static void print1ToMaxOfNDigits_Solution1(int n) {
        if (n <= 0) {
            return;
        }

        // 初始化数组，并将每个字符置为 '0'
        char[] arr = new char[n];
        for (int i = 0; i < n; i++) {
            arr[i] = '0';
        }
        // 递增打印
        while (!increment(arr)) {
            printNumber(arr);
        }
    }

    private static boolean increment(char[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        boolean isOverFlow = false; // 是否超过最大数值
        int nTakeOver = 0; // 是否进位
        for (int i = arr.length - 1; i >= 0; i--) {
            // arr[i] - '0' 的目的就是取到索引为 i 的整型数字，因为之前存到数组中的都是字符，减 '0' 后就变成了整型
            // 取到第 i 个字符，减 '0' 后变成整数，然后再加上进位符
            int nSum = arr[i] - '0' + nTakeOver;
            if (i == arr.length - 1) {
                // 每次将最低位进行加一
                nSum++;
            }
            // 如果发生了进位
            if (nSum >= 10) {
                if (i == 0) {
                    // 最高位发生进位，则已经达到最大值，发生溢出
                    isOverFlow = true;
                } else {
                    nSum -= 10;
                    nTakeOver = 1;
                    arr[i] = (char) (nSum + '0');
                }
            } else {
                arr[i] = (char) (nSum + '0');
                break;
            }
        }
        return isOverFlow;
    }

    // 方法二
    private static void print1ToMaxOfNDigits_Solution2(int n) {
        if (n <= 0) {
            return;
        }

        char[] nums = new char[n];
        for (int i = 0; i < 10; i++) {
            // 将整数转换成字符串
            String str = String.valueOf(i);
            // 将字符串中索引为 0 的字符赋值给数组中索引也为 0 的位置，charAt() 方法用于返回指定索引处的字符
            nums[0] = str.charAt(0);
            print1ToMaxOfDigitsRecursively(nums, n, 0);
        }
    }

    private static void print1ToMaxOfDigitsRecursively(char[] nums, int length, int index) {
        if (nums == null) {
            return;
        }
        if (index == length - 1) {
            printNumber(nums);
            return;
        }

        for (int i = 0; i < 10; i++) {
            String str = String.valueOf(i);
            // 将 index+1 处的元素设置为 i 的值
            nums[index + 1] = str.charAt(0);
            print1ToMaxOfDigitsRecursively(nums, length, index + 1);
        }
    }

    // 通用的打印输出方法
    private static void printNumber(char[] arr) {
        if (arr == null) {
            return;
        }

        boolean isBegin0 = true;
        for (char c : arr) {
            if (isBegin0 && c != '0') {
                isBegin0 = false;
            }
            if (!isBegin0) {
                System.out.print(c);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        print1ToMaxOfNDigits_Solution2(2);
        System.out.println(Arrays.toString(printNumbers(3)));
    }
}
