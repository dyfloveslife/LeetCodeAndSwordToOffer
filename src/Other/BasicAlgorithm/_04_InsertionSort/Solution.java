package Other.BasicAlgorithm._04_InsertionSort;

import java.util.Arrays;

/*
 * 插入排序
 *
 * 位置 1 的数比位置 0 的数小的话就交换；
 * 位置 2 的数比位置 1 的数小的话就交换，交换之后如果位置 1 的数还比位置 0 的数小的话，则继续交换；
 * ......
 *
 * 具体实现原理：
 * 1. 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入；
 * 2. 插入排序在实现上，通常只需用到 O(1) 的额外空间的排序；
 * 3. 因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
 *
 * 时间复杂度：和数据状况有关，稳定，在接近有序的情况下，表现优异。
 * 对于顺序数组：O(N)
 * 对于逆序数组：O(N^2)
 * 所以按最差情况来看，复杂度为 O(N^2)。
 */
public class Solution {
    public int[] insertionSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }

        // 外层 for 循环就是说：0~i-1 已经排好序了，那么当前的 i 应该如何排序
        for (int i = 1; i < nums.length; i++) {
            // nums[j] > nums[j + 1] 每次向前比较
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }

        return nums;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 2, 1, 5, 6, 4};
        int[] arr2 = {1, 2, 3, 2, 5, 6};
        int[] arr3 = {5, 2, 3, 1};
        int[] arr4 = {5, 1, 1, 2, 0, 0};

        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.insertionSort(arr1)));
        System.out.println(Arrays.toString(solution.insertionSort(arr2)));
        System.out.println(Arrays.toString(solution.insertionSort(arr3)));
        System.out.println(Arrays.toString(solution.insertionSort(arr4)));
    }
}
