package Other.BasicAlgorithm._04_InsertionSort;

import java.util.Arrays;

/**
 * 插入排序
 * <p>
 * 位置 1 的数比位置 0 的数小的话就交换；
 * 位置 2 的数比位置 1 的数小的话就交换，交换之后如果位置 1 的数还比位置 0 的数小的话，则继续交换；
 * ......
 * <p>
 * 具体实现原理：
 * 1. 通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入；
 * 2. 插入排序在实现上，通常只需用到 O(1) 的额外空间的排序；
 * 3. 因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
 * <p>
 * 时间复杂度：和数据状况有关，稳定，在接近有序的情况下，表现优异。
 * 对于顺序数组：O(N)
 * 对于逆序数组：O(N^2)
 * 所以按最差情况来看，复杂度为 O(N^2)。
 */
public class Solution {

    /**
     * Insertion sort.
     *
     * @param nums int[]
     * @return int[]
     */
    public int[] insertionSort1(int[] nums) {
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

    /**
     * Insertion sort.
     *
     * @param nums int[]
     * @return int[]
     */
    public int[] insertionSort2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        for (int i = 1; i < nums.length; i++) {
            int key = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j -= 1;
            }

            nums[j + 1] = key;
        }

        return nums;
    }

    /**
     * Swap item in array.
     *
     * @param nums int[]
     * @param i    int
     * @param j    int
     */
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Main function.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        int[] arr1 = {3, 2, 1, 5, 6, 4};
        int[] arr2 = {1, 2, 3, 2, 5, 6};
        int[] arr3 = {5, 2, 3, 1};
        int[] arr4 = {5, 1, 1, 2, 0, 0};
        int[] arr5 = {5, 2, 4, 6, 1, 3};

        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.insertionSort1(arr1)));
        System.out.println(Arrays.toString(solution.insertionSort1(arr2)));
        System.out.println(Arrays.toString(solution.insertionSort1(arr3)));
        System.out.println(Arrays.toString(solution.insertionSort1(arr4)));
        System.out.println(Arrays.toString(solution.insertionSort1(arr5)));

        int[] arr6 = {3, 2, 1, 5, 6, 4};
        int[] arr7 = {1, 2, 3, 2, 5, 6};
        int[] arr8 = {5, 2, 3, 1};
        int[] arr9 = {5, 1, 1, 2, 0, 0};
        int[] arr10 = {5, 2, 4, 6, 1, 3};

        System.out.println("---------");
        System.out.println(Arrays.toString(solution.insertionSort2(arr6)));
        System.out.println(Arrays.toString(solution.insertionSort2(arr7)));
        System.out.println(Arrays.toString(solution.insertionSort2(arr8)));
        System.out.println(Arrays.toString(solution.insertionSort2(arr9)));
        System.out.println(Arrays.toString(solution.insertionSort2(arr10)));
    }
}
