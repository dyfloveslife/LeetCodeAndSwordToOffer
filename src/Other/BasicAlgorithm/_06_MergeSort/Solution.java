package Other.BasicAlgorithm._06_MergeSort;

import java.util.Arrays;

/*
 * 归并排序
 *
 * 原理：
 * 0. 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
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
public class Solution {
    public int[] mergeSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }

        sortProcess(nums, 0, nums.length - 1);
        return nums;
    }

    private static void sortProcess(int[] nums, int left, int right) {
        // 当前范围只有一个数，则本身就已经排好序了
        if (left == right) {
            return;
        }

        int middle = left + ((right - left) >> 1);
        sortProcess(nums, left, middle);
        sortProcess(nums, middle + 1, right);
        merge(nums, left, middle, right);
    }

    // 左部分和右部分都已经排好序了，现在需要整体合并再排序
    private static void merge(int[] nums, int left, int middle, int right) {
        int[] help = new int[right - left + 1];
        
        // i 的作用就是在谁小填谁的时候，从 0 位置开始填
        int i = 0;
        // pos1 和 pos2 分别指向两个子数组的起始元素
        int pos1 = left;
        int pos2 = middle + 1;
        // 进行比较，谁小就将其填到 help 数组中
        while (pos1 <= middle && pos2 <= right) {
            help[i++] = nums[pos1] < nums[pos2] ? nums[pos1++] : nums[pos2++];
        }
        // 对于划分后的两部分来说，如果其中一部分的指针到头了，则将另一回部分的数直接放到 help 中
        while (pos1 <= middle) {
            help[i++] = nums[pos1++];
        }
        while (pos2 <= right) {
            help[i++] = nums[pos2++];
        }
        // 将 help 中的数拷贝到原数组中
        for (int j = 0; j < help.length; j++) {
            // 为什么要加一个 left？？？
            // 因为拷贝到原数组的过程也是在递归过程中拷贝的，在这过程中 left 是变化的
            nums[left + j] = help[j];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 5, 1, 7, -10, 4, 6, 12, 1};
        int[] nums2 = {5, 2, 3, 1};
        int[] nums3 = {5, 1, 1, 2, 0, 0};

        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.mergeSort(nums1)));
        System.out.println(Arrays.toString(solution.mergeSort(nums2)));
        System.out.println(Arrays.toString(solution.mergeSort(nums3)));
    }
}
