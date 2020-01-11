package SwordToOfferSolution._21_ReorderArray;

/*
 * 调整数组顺序使奇数位于偶数前面
 *
 * 题目描述：
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 *
 */
public class Solution {
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
        Solution array = new Solution();
        int[] a = new int[]{1, 2, 3, 4, 5};
        array.reorderArray(a);
        for (int num : a) {
            System.out.print(num);
        }
    }
}
