package Other.BasicAlgorithm._06_MergeSort;

import java.util.Arrays;

/**
 * Merge sort.
 */
public class Solution2 {

    /**
     * Merge sort.
     *
     * @param nums int[]
     * @return int[]
     */
    public int[] mergeSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }

        sortProcess(nums, 0, nums.length - 1);
        return nums;
    }

    /**
     * Sort process.
     *
     * @param nums  int[]
     * @param left  int
     * @param right int
     */
    private void sortProcess(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + ((right - left) >> 1);
        sortProcess(nums, left, mid);
        sortProcess(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    /**
     * Merge element.
     *
     * @param nums  int[]
     * @param left  int
     * @param mid   int
     * @param right right
     */
    private void merge(int[] nums, int left, int mid, int right) {
        // 计算两个子数组的大小
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // 将数据放到临时数组
        for (int i = 0; i < n1; i++) {
            leftArr[i] = nums[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = nums[mid + 1 + j];
        }

        // 合并临时数组
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] < rightArr[j]) {
                nums[k] = leftArr[i];
                i++;
            } else {
                nums[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // 复制剩余元素
        while (i < n1) {
            nums[k] = leftArr[i];
            k++;
            i++;
        }

        while (j < n2) {
            nums[k] = rightArr[j];
            k++;
            j++;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 5, 1, 7, -10, 4, 6, 12, 1};
        int[] nums2 = {5, 2, 3, 1};
        int[] nums3 = {5, 1, 1, 2, 0, 0};

        Solution2 solution = new Solution2();
        System.out.println(Arrays.toString(solution.mergeSort(nums1)));
        System.out.println(Arrays.toString(solution.mergeSort(nums2)));
        System.out.println(Arrays.toString(solution.mergeSort(nums3)));
    }
}
