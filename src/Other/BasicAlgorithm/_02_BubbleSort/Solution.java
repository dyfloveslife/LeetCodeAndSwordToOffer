package Other.BasicAlgorithm._02_BubbleSort;

/*
 * 冒泡排序
 * 第一次：0~end 位置比较，将大的数放在 end 位置；
 * 第二次，0~end-1 位置比较，将大的数放在 end-1 位置；
 * 第三次，0~end-2 位置比较，将大的数放在 end-2 位置；
 * ......
 *
 * 时间复杂度：O(N^2)，和数据状况无关，稳定。
 * 0~N-1，共 N 个数；
 * 0~N-2，共 N - 1 个数；
 * 0~N-3，共 N - 2 个数；
 * ......
 * 这是一个等差数列，最后的形式如同 a*N^2+b*N+1，舍弃低阶项、常数项和系数，则时间复杂度就是 O(N^2)。
 */
public class Solution {
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int end = arr.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void swap2(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
