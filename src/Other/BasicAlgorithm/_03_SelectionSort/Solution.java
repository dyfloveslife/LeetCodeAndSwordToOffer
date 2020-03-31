package Other.BasicAlgorithm._03_SelectionSort;

import java.util.Arrays;

/*
 * 选择排序
 *
 * 每一轮选择最小元素交换到未排定部分的开头。
 * 从 0~N-1 中选择一个最小的数，和 0 位置上的数交换；
 * 从 1~N-1 中选择一个最小的数，和 1 位置上的数交换；
 * 从 2~N-1 中选择一个最小的数，和 2 位置上的数交换；
 * ...
 *
 * 即首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 以此类推，直到所有元素均排序完毕。
 *
 * 时间复杂度：O(N^2)，N 为数组的长度，和数据状况无关，不稳定。
 * 空间复杂度：O(1)，使用到常数个临时变量。
 */
public class Solution {
    public int[] selectSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }

        // [0, i) 有序，且该区间的所有元素就是最终排序后的样子
        for (int i = 0; i < nums.length - 1; i++) {
            // 选择区间 [i, len-1] 里最小元素的索引，与 i 交换
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                // 从 i+1 开始往后找，如果能够找到一个比当前 minIndex 上的数小的话，
                // 则将最小数的索引保存
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
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
        System.out.println(Arrays.toString(solution.selectSort(arr1)));
        System.out.println(Arrays.toString(solution.selectSort(arr2)));
        System.out.println(Arrays.toString(solution.selectSort(arr3)));
        System.out.println(Arrays.toString(solution.selectSort(arr4)));
    }
}
