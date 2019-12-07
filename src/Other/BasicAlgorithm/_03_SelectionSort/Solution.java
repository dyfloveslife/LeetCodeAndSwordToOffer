package Other.BasicAlgorithm._03_SelectionSort;

/*
 * 选择排序
 * 每次从后面的数中选择最小的数，将其放到前面去。
 * 从 0~N-1 中选择一个最小的数，和 0 位置上的数交换；
 * 从 1~N-1 中选择一个最小的数，和 1 位置上的数交换；
 * 从 2~N-1 中选择一个最小的数，和 2 位置上的数交换；
 * ......
 *
 * 时间复杂度：O(N^2)，和数据状况无关，不稳定。
 */
public class Solution {
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
