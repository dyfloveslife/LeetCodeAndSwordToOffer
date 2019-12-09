package Other.BasicAlgorithm._11_HeapSort;

/*
 * 堆排序
 * 不稳定
 * 时间复杂度：O(N*logN)
 * 空间复杂度：O(1)
 *
 * 建立大根堆的时候，每插入一个值，则只和树的高度有关。代价是 O(logN)
 * 第 i 个节点加入到 0~i-1 中，代价是 log(i-1)，因此，所有节点加入的代价
 * 就是 log1+log2+log3+...+logN-1=O(N)
 *
 * heapify 过程：
 * 如果堆中某个数变了，不满足大顶堆条件，则需要进行堆化处理。
 * 找到当前节点的左右孩子，将当前节点与左右孩子中最大的交换，依次执行下去即可。
 *
 * 减堆操作：
 * 拿大根堆来说，将根节点与最后一个节点进行交换，然后堆的长度减一，
 * 此时不一定满足大根堆的要求，所以要进行调整(heapify)成大根堆。
 *
 * 堆排序：
 * 1. 先建立大根堆，然后堆顶和堆中最后一个元素交换，此时最大的数就是数组中最大的数；
 * 2. 交换完成后，在 0~heapSize-1 的范围里进行 heapify 的过程，重新调整成大根堆；
 * 3. 每次都这么做，直到堆中的元素减完，最后就将原先的无需的数组排好序了。
 */
public class Solution {
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        // 堆排序的过程：不断的将堆顶与堆中最后一个元素交换，然后再 heapify
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    // 从 0~(i-1) 位置已经构成大根堆了，现在需要把第 i 的位置上的元素加进去
    private static void heapInsert(int[] arr, int index) {
        // 如果当前元素比父节点元素大，则交换
        // 然后当前元素的索引更新成原先父节点的索引
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        int right = left + 1;

        while (left < heapSize) {
            // 左右孩子选出较大的
            // 如果右孩子越界，即 right < heapSize，则最大的就是左孩子
            int largest = right < heapSize && arr[left] < arr[right] ? right : left;
            // 从左右孩子中选出较大的之后，还得判断和当前节点的大小。
            largest = arr[largest] > arr[index] ? largest : index;
            // 如果当前节点变了之后还是最大的，则就不往下沉了
            // 也就是当前节点和孩子之间的最大值还是当前节点，就不需要再往下沉了
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
