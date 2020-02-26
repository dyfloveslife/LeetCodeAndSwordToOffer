package SwordToOfferSolution._21_ReorderArray;

import java.util.Arrays;

/*
 * 调整数组顺序使奇数位于偶数前面
 *
 * 题目描述：
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 *
 * 思路：
 * 1. 使用双指针，left 从左向右直到遇到偶数，right 从右向左直到遇到奇数；
 * 2. 将 left 和 right 所指的元素进行交换；
 * 3. 直到 left 和 right 相遇位置；
 * 4. 也可以使用快慢指针，一个走两格，一个走一格。
 */
public class Solution {

    public static int[] exchange(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        int start = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 1) {
                swap(arr, i, start);
                start++;
            }
        }
        return arr;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void reorderArray(int[] array) {
        int oddCount = 0;
        for (int x : array) {
            if (!isEven(x)) {
                oddCount++;
            }
        }

        int[] copyArray = array.clone();
        int i = 0, j = oddCount;
        for (int num : copyArray) {
            if (num % 2 == 1) {
                array[i++] = num;
            } else {
                array[j++] = num;
            }
        }
    }

    private boolean isEven(int x) {
        return x % 2 == 0;
    }

    public static void main(String[] args) {
//        Solution array = new Solution();
//        int[] a = new int[]{1, 2, 3, 4, 5};
//        array.reorderArray(a);
//        for (int num : a) {
//            System.out.print(num);
//        }
        int[] arr = {1, 2, 3, 4};
        System.out.println(Arrays.toString(exchange(arr)));
    }
}
