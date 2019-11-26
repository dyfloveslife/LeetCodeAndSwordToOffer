package SwordToOfferSolution._40_KLeastNumbers;

import java.util.ArrayList;

/**
 * 最小的 K 个数
 * 思路 1：可以修改原数组的情况
 * 利用快速排序中的获取分割（中轴）点位置函数 partition。
 * 基于数组的第 k 个数字来调整，使得比第 k 个数字小的所有数字都位于数组的左边，比第 k 个数字大的所有数字都位于数组的右边。
 * 调整之后，位于数组左边的 k 个数字就是最小的 k 个数字（这 k 个数字不一定是排序的）
 *
 * 思路 2：不可修改原数组
 */
public class Solution {
    private ArrayList<Integer> getLeastNumbers(int[] nums, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (nums == null || k > nums.length || k <= 0) return res;
        findKthSmallest(nums, k - 1);
        for (int i = 0; i < k; i++) {
            res.add(nums[i]);
        }
        return res;
    }

    // 将数组 nums 进行按照 k 进行划分，小于 k 的都放在其左边，大于 k 的都放在 k 的右边。
    // 这样的话，前 k 个数都会在 k 的左边。
    private void findKthSmallest(int[] nums, int k) {
        int low = 0, height = nums.length - 1;
        while (low < height) {
            // partition() 方法返回一个 index，该 index 满足 nums[low, index-1] <= nums[index] <= nums[index+1, height]。
            int index = partition(nums, low, height);
            if (index == k) break;
            // k 在 index 的左侧，则调整 height
            if (index > k) height = index - 1;
            // k 在 index 的右侧，则调整 low
            if (index < k) low = index + 1;
        }
    }

    private int partition(int[] nums, int low, int height) {
        int pivot = nums[low];
        int i = low, j = height + 1;
        while (true) {
            // 保持头尾两个指针向中间扫描，每次在头部找到大于pivot的值，同时在尾部找到小于pivot的值，
            // 然后将它们做一个交换，就可以一次把这两个数字放到最终的位置。
            // 从左到右，不断找到小于 pivot 的数
            while (i != height && nums[++i] < pivot) ;
            // 从右到左，不断找到大于 pivot 的数
            while (j != low && nums[--j] > pivot) ;
            if (i >= j) break;
            swap(nums, i, j);
        }
        swap(nums, low, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
