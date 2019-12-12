package Other.BasicAlgorithm._06_MergeSort;

import java.util.Arrays;

/*
 * 归并排序
 * 1. 先将数组分成两部分，然后分别将这两部分排好序；
 * 2. 然后开一个数组，指针 a 和 b 分别指向这两部分的第一个元素；
 * 3. 比较 a 和 b 的大小，小的放进数组中。如果某个指针到了末尾的话，则另一个指针所指元素的
 *    后面所有的数直接进数组。
 * 4. 最后再将排好序的顺序拷贝到原数组中。
 *
 * 时间复杂度：O(N*logN)
 * 空间复杂度：O(N)
 * 稳定
 */
public class Soluion {
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        sortProcess(arr, 0, arr.length - 1);
    }

    private static void sortProcess(int[] arr, int left, int right) {
        // 当前范围只有一个数，则本身就已经排好序了
        if (left == right) {
            return;
        }

        int middle = left + ((right - left) >> 1);
        sortProcess(arr, left, middle);
        sortProcess(arr, middle + 1, right);
        merge(arr, left, middle, right);
    }

    // 左部分和右部分都已经排好序了，现在需要整体合并再排序
    private static void merge(int[] arr, int left, int middle, int right) {
        int[] help = new int[right - left + 1];
        // i 的作用就是在谁小填谁的时候，从 0 位置开始填
        int i = 0;
        int position1 = left;
        int position2 = middle + 1;
        // 进行比较，谁小就将其填到 help 数组中
        while (position1 <= middle && position2 <= right) {
            help[i++] = arr[position1] < arr[position2] ? arr[position1++] : arr[position2++];
        }
        // 对于划分后的两部分来说，如果其中一部分的指针到头了，则将另一回部分的数直接放到 help 中
        while (position1 <= middle) {
            help[i++] = arr[position1++];
        }
        while (position2 <= right) {
            help[i++] = arr[position2++];
        }
        // 将 help 中的数拷贝到原数组中
        for (int j = 0; j < help.length; j++) {
            // 为什么要加一个 left？？？
            // 因为拷贝到原数组的过程也是在递归过程中拷贝的，在这过程中 left 是变化的
            arr[left + j] = help[j];
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 1, 7, -10, 4, 6, 12, 1};
        System.out.println(Arrays.toString(arr));
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
