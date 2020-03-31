package Other.BasicAlgorithm._02_BubbleSort;

import java.util.Arrays;

/*
 * 冒泡排序
 *
 * 第一次：0~end 位置比较，将大的数放在 end 位置；
 * 第二次，0~end-1 位置比较，将大的数放在 end-1 位置；
 * 第三次，0~end-2 位置比较，将大的数放在 end-2 位置；
 * ......
 *
 * 时间复杂度：O(N^2)，和数据状况无关，稳定。
 * 0~N-1，共 N 个数；
 * 0~N-2，共 N - 1 个数；
 * 0~N-3，共 N - 2 个数；
 * ......
 * 这是一个等差数列，最后的形式如同 a*N^2+b*N+1，舍弃低阶项、常数项和系数，则时间复杂度就是 O(N^2)，N 表示数组的长度。
 * 而空间复杂度为 O(1)，使用到常数个临时变量。
 *
 * 此外，还可以使用一个 boolean 遍历，当数组中已经排好序的时候，则不必再进行后续的排序。
 */
public class Solution {
    public int[] bubbleSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }

        for (int end = nums.length - 1; end > 0; end--) {
            // 一开始默认数组是有序的，只要发生了一次交换，则必须进行下一轮比较，
            // 如果在内层循环中，没有执行一次交换操作，则说明此数组已经是排好序的数组
            boolean isSorted = true;
            for (int i = 0; i < end; i++) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                    isSorted = false;
                }
            }
            // 如果已经是排好序的，则直接 break
            if (isSorted) {
                break;
            }
        }
        return nums;
    }

    public void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    public void swap2(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 0, 6, 1, 3, 5};
        int[] arr2 = {5, 1, 1, 2, 0, 0};

        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.bubbleSort(arr1)));
        System.out.println(Arrays.toString(solution.bubbleSort(arr2)));
    }
}
