package LeetCodeSolution.AlgorithmThought._01_DoublePointer._88_MergeSortedArray;

import java.util.Arrays;

/*
 * 合并两个有序数组
 *
 * 题目描述：
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 * 思路：
 * 1. 如果使用双指针，从前往后遍历的话，由于 nums1 是输出数组，则需要事先将 nums1 中的数放在别的地方，无异于浪费了辅助空间；
 * 2. 可以使用双指针，从后往前遍历，用指针 p 追踪添加元素的位置，p1 和 p2 进行比较；
 * 3. 如果 p1 所指元素小于 p2 所指元素，则将 p2 所指元素赋值给 p 所指元素，然后指针前移；
 * 4. 否则，将 p1 所指元素赋值给 p 所指元素，同样的指针前移；
 * 5. 当 p1 小于 0 时，此时 nums2 还有数据未完全拷贝，则将其直接拷贝到 nums1 的前面。
 * 6.        p1       p
 *   [ 1  2  3  0  0  0  0]
 *   [-1  5  6]
 *           p2
 * 7. 也就是比较 p1 和 p2 所指元素的大小，将大的放到 p 的位置，然后索引减一；
 * 8. 最后，如果 nums2 还有剩余元素的话，则直接赋值到 p 位置上即可；
 * 9. 时间复杂度为 O(m + n)，空间复杂度为 O(1)。
 */
public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null) {
            return;
        }
        // p1、p2 分别来到 nums1、nums2 中的最后一个元素
        int p1 = m - 1, p2 = n - 1;
        // p 来到合并之后 nums1 的最后一个位置
        int p = m + n - 1;

        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }

        // 如果 nums2 中还有剩余的话，则直接复制给 nums1 对应的位置
        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};

        solution.merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1)); // [1, 2, 2, 3, 5, 6]

        int[] nums3 = {4, 5, 6, 0, 0, 0};
        int[] nums4 = {1, 2, 3};
        solution.merge(nums3, 3, nums4, 3);
        System.out.println(Arrays.toString(nums3)); // [1, 2, 3, 4, 5, 6]
    }
}
