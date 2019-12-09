package Other.BasicAlgorithm._06_MergeSort;

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
        if (left == right) {
            return;
        }

        int middle = left + ((right - left) >> 1);
        sortProcess(arr, left, middle);
        sortProcess(arr, middle + 1, right);
        merge(arr, left, middle, right);
    }

    private static void merge(int[] arr, int left, int middle, int right) {
        int[] help = new int[right - left + 1];
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
        for (i = 0; i < help.length; i++) {
            arr[right + i] = help[i];
        }
    }
}
