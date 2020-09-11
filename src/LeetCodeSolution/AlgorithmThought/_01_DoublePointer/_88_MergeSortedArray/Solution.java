package LeetCodeSolution.AlgorithmThought._01_DoublePointer._88_MergeSortedArray;

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
 * 6.       p1       p
 *   [1  2  3  0  0  0]
 *   [2  5  6]
 *         p2
 * 7. 也就是比较 p1 和 p2 所指元素的大小，将大的放到 p 的位置，然后索引减一；
 * 8. 最后，如果 nums2 还有剩余元素的话，则直接赋值到 p 位置上即可。
 */
public class Solution {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null) {
            return;
        }
        // p1 来到 nums1 中的最后一个元素
        int p1 = m - 1;
        // p2 来到 nums2 中的最后一个元素
        int p2 = n - 1;
        // 合并以后，数组的总长度就是 m+n
        // p 来到合并之后 nums1 的最后一个位置，这个位置是没有元素的位置，
        // 也就是等待被填入元素的位置
        int p = m + n - 1;

        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }
        // 从原数组 nums2 的 0 位置开始到 p2+1 位置结束，复制给目标数组从 0 位置开始的 num1
        // System.arraycopy(nums2, 0, nums1, 0, p2 + 1);

        // 如果 nums2 中还有剩余的话，则直接复制给 nums1 对应的位置
        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 0, 0, 0};
        int[] b = {2, 5, 6};

        // wrong array, does not meet the requirements
        int[] c = {3, 0, 5, 0, 7, 0};
        int[] d = {1, 3, 6};
        Solution.merge(a, 3, b, 3);
        for (int i : a) {
            System.out.print(i + " ");
        }
    }
}
