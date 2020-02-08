package Other.BasicAlgorithm._10_QuickSort;

/* 不稳定的排序算法：快选希堆
 *
 * 快速排序
 * 不稳定
 * 时间复杂度：O(N*logN)
 * 空间复杂度：O(logN)
 *
 * 经典快排：
 * 选取数组的最后一个数 x，将小于等于 x 的放在左边，大于 x 的放在右边，然后对于左右两部分按照同样的方式进行递归处理。
 * 一次划分只搞定一个位置上的数。
 * 缺点：和数据规模有关系，例如{1,2,3,4,5}，每次拿最后一个去划分，则对于每个数都需要O(N)的时间，总的时间复杂度就是 O(N^2)。
 * 如果划分得很好，左部分和右部分的数据规模大体相同的话，则时间复杂度就是 O(N*logN)。
 *
 * 对经典快排进行改进：
 * 选取数组的最后一个数 x，将小于 x 的放在左边，大于 x 的放在右边，等于 x 放在中间，然后对于左右两部分按照同样的方式进行递归处理。
 * 一次划分搞定多个等于 x 的位置上的数。
 *
 * 随机快排：
 * 从数组中随机选一个数和最后一个数进行交换，用这个随机选择的数进行划分。这就成了一个概率问题。长期期望就是 O(N*logN)的。
 *
 * 绕开数据本身的数据状况，可以使用随机选举的方式和哈希。
 *
 * 归并排序输在需要开辟一个 O(N) 的辅助空间，其次常数项也比快排多。
 *
 * 为什么随机快排的额外空间复杂度是 O(logN)?
 * 用在划分点上了，只有知道了划分点的位置，左侧递归完事儿之后才能知道右边从哪个位置开始划分。
 * 而左右两个部分都需要分别记录划分点。
 * 然而在哪儿打断点也是一个概率问题，如果好的情况下，每次打在中间的位置，就像一个二叉树，则时间复杂度是 O(logN)；
 * 如果断点每次都打在一遍，就像向左偏或向右偏的二叉树，则时间复杂度就是 O(N)。
 */
public class Solution {
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        quickSortCore(arr, 0, arr.length - 1);
    }

    private static void quickSortCore(int[] arr, int left, int right) {
        if (left < right) {
            // 随机快排
            swap(arr, left + (int) (Math.random() * (right - left + 1)), right);

            int[] p = partition(arr, left, right);
            quickSortCore(arr, left, p[0] - 1);
            quickSortCore(arr, p[1] + 1, right);
        }
    }

    // 荷兰国旗问题（稍微有些不同）
    // 该函数返回的是等于数组最后一个元素 x 的范围的左右边界
    // p[0] 代表等于 x 的左边界，p[1] 代表等于 x 的右边界
    private static int[] partition(int[] arr, int left, int right) {
        int less = left - 1;
        // 这里将 大于区域的范围 直接定位到 right
        int more = right;

        while (left < more) {
            // 小于 num 的情况
            if (arr[left] < arr[right]) {
                swap(arr, ++less, left++);
                // 大于 num 的情况，此时 left 需要留在原地，继续考察与 num 的大小关系
            } else if (arr[left] > arr[right]) {
                swap(arr, --more, left);
                // 等于 num 的情况
            } else {
                left++;
            }
        }
        // 由于改进后的 partition 函数，初始的时候大于 x 的区域是包含 x 的，所以在划分完之后，
        // 需要将最后一个 x 的位置与 大于 x 区域的第一个数交换，这样就实现了小于 x 的在左边，
        // 等于 x 的在中间，大于 x 的在右边
        // 也就是说 x 一开始就不参与遍历，最后通过 swap 让其归位
        swap(arr, more, right);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 5, 6, 4};
        quickSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
