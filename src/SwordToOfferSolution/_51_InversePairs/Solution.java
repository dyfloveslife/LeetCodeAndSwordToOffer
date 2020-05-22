package SwordToOfferSolution._51_InversePairs;

/*
 * 数组中的逆序对的数量
 *
 * 题目描述：
 * 在数组中的两个数字如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组，求出这个数组中的逆序对的总数。
 *
 * 思路：
 * 采用归并排序的思想，先把数组分成子数组，统计出子数组内部的逆序对数，然后再统计两个相邻数组之间的逆序对数，并且在统计的过程中进行排序。
 * 1. 先将数组划分成左右两部分，下面以左部分为例：
 * 2. 左部分直到划分为长度为 2 的子数组，然后再划分为长度为 1 的子数组，比较两个数的大小；
 *      如果左边大于右边，则构成一个逆序对，并进行合并、排序；否则不构成逆序对。
 * 3. 等到最终要准备合并为一个整体数组的时候，用 p1、p2、p3 指针分别指向左部分子数组的最后一个数、右部分子数组的最后一个数以及最终结果数组的最后一个数；
 * 4. 如果 p1 > p2，则将 p1 加入到最终的结果数组中，此时统计 p2 指向的数与其前面一共有多少数，有多少个数就有几对逆序对，
 *      因为 p1 指向的数都要比 p2 指向的子数组中的数要大，并且 p1 指针左移，p3 也要左移；
 * 5. 如果 p1 < p2，则说明没有逆序对，此时将 p2 所指的数加入到 p3 的位置即可；
 * 6. 总之，谁大就移动谁，然后将较大的数复制到 p3 所指向的最终结果数组中即可。
 */
public class Solution {
    int res;

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        res = 0;
        inversePairsCoreMergeSort(nums, 0, nums.length - 1);
        return res;
    }

    private void inversePairsCoreMergeSort(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }

        int middle = left + ((right - left) >> 1);
        inversePairsCoreMergeSort(nums, left, middle);
        inversePairsCoreMergeSort(nums, middle + 1, right);
        merge(nums, left, middle, right);
    }

    private void merge(int[] nums, int left, int middle, int right) {
        int[] help = new int[right - left + 1];
        int index = 0;
        int pos1 = left;
        int pos2 = middle + 1;

        while (pos1 <= middle && pos2 <= right) {
            if (nums[pos1] <= nums[pos2]) {
                help[index++] = nums[pos1++];
            } else if (nums[pos1] > nums[pos2]) {
                help[index++] = nums[pos2++];
                // 统计逆序对的数量
                // 如果第一个子数组中数字大于第二个子数组中的数字，则构成逆序对，
                // 并且逆序对的数目等于第二个子数组中剩余数字的个数
                // nums[pos1] > nums[pos2]，从 a[pos1] 开始到 a[middle] 必定都是大于这个 a[pos2] 的，
                // 因为此时分治的两边已经是各自有序了
                res += (middle - pos1 + 1);
                // res %= 1000000007;
            }
        }

        while (pos1 <= middle) {
            help[index++] = nums[pos1++];
        }
        while (pos2 <= right) {
            help[index++] = nums[pos2++];
        }

        for (int j = 0; j < help.length; j++) {
            nums[left + j] = help[j];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {7, 5, 6, 4};
        int[] nums2 = {1, 3, 2, 3, 1};
        int[] nums3 = {4, 5, 6, 7};

        System.out.println(solution.reversePairs(nums1));
        System.out.println(solution.reversePairs(nums2));
        System.out.println(solution.reversePairs(nums3));
    }
}
