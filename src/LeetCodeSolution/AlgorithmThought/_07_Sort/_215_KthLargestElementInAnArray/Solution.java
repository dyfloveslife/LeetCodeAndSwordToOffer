package LeetCodeSolution.AlgorithmThought._07_Sort._215_KthLargestElementInAnArray;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * 数组中的第 K 个最大元素
 *
 * 题目描述：
 * 在未排序的数组中找到第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 思路：
 * 1. 排序：直接调用自带的排序方法将数组排好序后，返回数组的长度减去 k 即可。O(NlogN),O(1)
 * 2. 堆：使用小顶堆，不断的将数组中的数加入到堆中，但是如果堆的数量大于了 k，则需要弹出堆中元素，
 *        也就是说堆中的元素需要保证始终都是 k 个，然后等到遍历完所有的数之后，堆顶的元素就是第 k 个最大的元素。O(NlogK),O(K)
 * 3. 快速选择算法：注意 partition 函数的作用。O(N),O(1)
 */
public class Solution {

    // 排序
    public static int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    // 堆
    public static int findKthLargest2(int[] nums, int k) {
        // 默认小顶堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    // 快速选择算法
    public static int findKthLargest3(int[] nums, int k) {
        k = nums.length - k;
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int j = partition(nums, low, high);
            if (j == k) {
                break;
            } else if (j < k) {
                // 从右部分开始找
                low = j + 1;
            } else {
                // 从左部分开始找
                high = j - 1;
            }
        }
        return nums[k];
    }

    // 此函数的作用是以数组中的第一个数作为基准，将小于等于该数的放在数组的左边，
    // 大于该数的放在数组的右边，最后返回该数在数组中的索引
    public static int partition(int[] arr, int low, int high) {
        int i = low;
        // high 传过来的是数组的索引右界
        int j = high + 1;
        while (true) {
            // arr[++i] < arr[low] 表示以索引为 low 位置的数 x 作为基准，向右一直找比 x 大的数才停，将 i 的位置确定下来
            // i < h 表示索引 i 不能越右界
            while (arr[++i] < arr[low] && i < high) ;
            // arr[--j] > arr[low] 表示以索引为 low 位置的数 x 作为基准，向左一直找比 x 小的数才停，将 j 的位置确定下来
            // j > l 表示 j 不能越左界
            while (arr[--j] > arr[low] && j > low) ;
            // 如果 i 位于 j 的右边了，或者 j 位于 i 的左边了，则退出循环
            if (i >= j) {
                break;
            }
            // 如果 i < j，则需要交换 i 和 j 所指向的元素
            swap(arr, i, j);
        }
        // 将索引为 low 上的基准数与 j 交换，因为 j 上的数比 low 上的数要小
        swap(arr, low, j);
        // 返回索引 j
        return j;
    }

    public static int partition2(int[] arr, int left, int right) {
        int less = left - 1;
        int more = right;

        while (left < more) {
            if (arr[left] < arr[right]) {
                swap(arr, ++less, left++);
            } else if (arr[left] > arr[right]) {
                swap(arr, --more, left);
            } else {
                left++;
            }
        }
        swap(arr, more, right);
        return more;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 2, 1, 5, 6, 4};
        System.out.println(findKthLargest2(arr1, 2)); // 5

        int[] arr2 = {4, 2, 3, 1, 2, 3, 5, 5, 6};
        System.out.println(findKthLargest3(arr2, 4)); // 4

        System.out.println(partition(arr1, 0, arr1.length - 1));
    }
}
