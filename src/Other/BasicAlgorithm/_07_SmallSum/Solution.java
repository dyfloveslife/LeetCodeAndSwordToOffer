package Other.BasicAlgorithm._07_SmallSum;

/*
 * 小和问题
 * 在一个数组中，将每一个元素左边比当前元素小的元素累加起来，叫做这个数组的小和。
 *
 * 思路：
 * 1) 将当前序列分为两个子序列，分别求其小和；
 * 2) 对 1) 划分得到的两个子序列进行 merge 操作，得到合并过程之后产生的小和，再加上 1) 得到的两个子序列产生的小和之和；即为总的小和；
 * 3) 递归地执行 1) 和 2)。
 *
 * merge　操作采用二路归并排序的思想。
 * 求一个数组的小和，可以转化为求每个元素在小和累加过程出现的次数，然后将当前元素与出现次数相乘，累加得到小和。
 * 假设当前元素为 a，a 的右边比 a 大的元素个数则为 a 在小和累加过程出现的次数。
 */
public class Solution {
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        return mergeSort(arr, 0, arr.length - 1);
    }

    private static int mergeSort(int[] arr, int left, int right) {
        if (left == right) return 0;
        int middle = left + ((right - left) >> 1);
        return mergeSort(arr, left, middle)
                + mergeSort(arr, middle + 1, right)
                + merge(arr, left, middle, right);
    }

    private static int merge(int[] arr, int left, int middle, int right) {
        int[] help = new int[right - left + 1];
        int position1 = left;
        int position2 = middle + 1;
        int i = 0;
        // 统计小和的个数
        int res = 0;
        while (position1 <= middle && position2 <= right) {
            // 如果指针 position1 对应的数比指针 position2 对应的数小，
            // 则指针 position1 对应的数的小和的个数就是从 position2 到 right 的个数再乘以 arr[position1]。
            res += arr[position1] < arr[position2] ? (right - position2 + 1) * arr[position1] : 0;
            help[i++] = arr[position1] < arr[position2] ? arr[position1] : arr[position2];
        }
        while (position1 <= middle)
            help[i++] = arr[position1];
        while (position2 <= right)
            help[i++] = arr[position2];
        for (i = 0; i < help.length; i++)
            arr[right + i] = help[i];
        return res;
    }
}
