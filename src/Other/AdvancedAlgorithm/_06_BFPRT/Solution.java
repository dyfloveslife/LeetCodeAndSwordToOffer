package Other.AdvancedAlgorithm._06_BFPRT;

import java.util.Arrays;

/*
 * BFPRT 算法
 *
 * 用来在一个无序数组中，找到前 K 小的数或前 K 大的数。
 *
 * 思路：
 * 与快速排序的不同之处在于，快速排序是递归的对左右两边进行搜索，其所选择的数是随机的，
 * 而 BFPRT 算法在选择数的时候是有条件的选择中位数。时间复杂度能够做到绝对的 O(N)。
 */
public class Solution {

    public static int[] getMinKNumsByBFPRT(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return arr;
        }

        int minKth = getMinKthByBFPRT(arr, k);
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i != arr.length; i++) {
            if (arr[i] < minKth) {
                res[index++] = arr[i];
            }
        }
        for (; index != res.length; index++) {
            res[index] = minKth;
        }
        return res;
    }

    private static int getMinKthByBFPRT(int[] arr, int k) {
        int[] copyArr = copyArray(arr);
        return bfprt(copyArr, 0, copyArr.length - 1, k - 1);
    }

    private static int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i != arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // BFPRT 算法主逻辑
    // 在 begin 和 end 范围内求第 i 小的数
    private static int bfprt(int[] arr, int begin, int end, int i) {
        if (begin == end) {
            return arr[begin];
        }

        // 使用中位数数组中的的中位数作为划分值
        // 这是和快排 partition 的区别
        int pivot = medianOfMedians(arr, begin, end);
        // partition 即荷兰国旗的划分问题，返回的是等于给定值的区域
        int[] pivotRange = partition(arr, begin, end, pivot);
        // 如果 i 命中了，则直接返回
        if (i >= pivotRange[0] && i <= pivotRange[1]) {
            return arr[i];
        } else if (i < pivotRange[0]) {
            return bfprt(arr, begin, pivotRange[0] - 1, i);
        } else {
            return bfprt(arr, pivotRange[1] + 1, end, i);
        }

    }

    private static int medianOfMedians(int[] arr, int begin, int end) {
        int num = end - begin + 1;
        // 每 5 个数是一组，最后不满 5 个数的用 offset 补齐
        int offset = num % 5 == 0 ? 0 : 1;
        int[] mArr = new int[num / 5 + offset];
        for (int i = 0; i < mArr.length; i++) {
            int beginI = begin + i * 5;
            int endI = beginI + 4;
            // 把中位数数组拿出来，调用自己，再求中位数
            mArr[i] = getMedian(arr, beginI, Math.min(end, endI));
        }
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }


    // 返回等于 pivotValue 的中间区域的两个下标构成的数组
    private static int[] partition(int[] arr, int begin, int end, int pivotValue) {
        int less = begin - 1;
        int more = end + 1;
        int cur = begin;
        while (cur != more) {
            if (arr[cur] < pivotValue) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivotValue) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        int[] range = new int[2];
        range[0] = less + 1;
        range[1] = more - 1;
        return range;
    }


    private static int getMedian(int[] arr, int begin, int end) {
        insertionSort(arr, begin, end);
        int sum = end + begin;
        int mid = (sum / 2) + (sum % 2);
        return arr[mid];
    }

    private static void insertionSort(int[] arr, int begin, int end) {
        for (int i = begin + 1; i != end + 1; i++) {
            for (int j = i; j != begin; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {6, 9, 1, 3, 1, 2};
        System.out.println(Arrays.toString(getMinKNumsByBFPRT(arr, 4)));
    }
}