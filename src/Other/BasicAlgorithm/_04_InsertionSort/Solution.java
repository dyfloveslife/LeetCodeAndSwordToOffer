package Other.BasicAlgorithm._04_InsertionSort;

/*
 * 插入排序
 * 位置 1 的数比位置 0 的数小的话就交换；
 * 位置 2 的数比位置 1 的数小的话就交换，交换之后如果位置 1 的数还比位置 0 的数小的话，则继续交换；
 * ......
 *
 * 外层 for 循环就是说：0~i-1 已经排好序了，那么当前的 i 应该如何排序。
 * arr[j] > arr[j + 1] 每次向前比较。
 *
 * 时间复杂度：和数据状况有关，稳定。
 * 对于顺序数组：O(N)
 * 对于逆序数组：O(N^2)
 * 所以按最差情况来看，复杂度为 O(N^2)。
 */
public class Solution {
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >=0 && arr[j] > arr[j + 1]; j--)
                swap(arr, j, j + 1);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
