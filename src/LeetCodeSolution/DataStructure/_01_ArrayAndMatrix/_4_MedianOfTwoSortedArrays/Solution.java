package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._4_MedianOfTwoSortedArrays;

/*
 * 寻找两个正序数组的中位数
 *
 * 题目描述：
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 思路一：使用额外的辅助空间
 * 1. 常规的做法是：先将两个数组合并，然后再排序，根据奇偶进行判断；
 * 2. 当然也可以所用三个指针，逐个比较大小即可；
 * 3. 时间复杂度为 O(m+n)。
 *
 * 思路二：二分
 * 1. 详细解释：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/he-bing-yi-hou-zhao-gui-bing-guo-cheng-zhong-zhao-/
 * 2. https://www.youtube.com/watch?v=ScCg9v921ns
 * 3. 可以使用二分法，通过二分法找到“边界线”：
 *    对于偶数来说，“边界线”的两侧分别为 left_max 和 right_min，然后直接取平均值就好了；
 *    对于奇数来说，取“边界线”的左侧作为中位数。
 * 4. 在有序数组中找中位数，其实就是找“边界线”，边界线找对了，中位数也就知道了；
 * 5. 也就是说，在较短的那个数组中找到分割线，使得 L1 ≤ R1，L2 ≤ R2；
 * 6. 为什么选择“较短的那个数组”，因为对于较短的数组来说，我去找它的分割线的时候，用时也是很短的；
 */
public class Solution {
    // 思路一
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int[] mergeResult = merge(nums1, nums2);
        int n = mergeResult.length;
        return (n & 1) == 1 ? (mergeResult[n / 2]) : (mergeResult[n / 2 - 1] + mergeResult[n / 2]) / 2.0;
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < nums1.length && j < nums2.length) {
            nums[k++] = (nums1[i] < nums2[j]) ? nums1[i++] : nums2[j++];
        }
        while (i < nums1.length) {
            nums[k++] = nums1[i++];
        }
        while (j < nums2.length) {
            nums[k++] = nums2[j++];
        }
        return nums;
    }

    // 思路二
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 > len2) {
            return findMedianSortedArrays2(nums2, nums1);
        }
        // 如果 nums1 没有元素，则需要从 nums2 中找到中位数
        if (len1 == 0) {
            return (nums2[(len2 - 1) / 2] + nums2[len2 / 2]) / 2.0;
        }
        int len = len1 + len2;
        int start = 0;
        int end = len1;
        int curA, curB;
        while (start <= end) {
            curA = (start + end) / 2;
            curB = (len + 1) / 2 - curA;
            double L1 = curA == 0 ? Integer.MIN_VALUE : nums1[curA - 1];
            double L2 = curB == 0 ? Integer.MIN_VALUE : nums2[curB - 1];
            double R1 = curA == len1 ? Integer.MAX_VALUE : nums1[curA];
            double R2 = curB == len2 ? Integer.MAX_VALUE : nums2[curB];
            if (L1 > R2) {
                end = curA - 1;
            } else if (L2 > R1) {
                start = curA + 1;
            } else {
                return (len & 1) == 1 ? Math.max(L1, L2) : (Math.max(L1, L2) + Math.min(R1, R2)) / 2;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};

        System.out.println(solution.findMedianSortedArrays1(nums1, nums2));
        System.out.println(solution.findMedianSortedArrays2(nums1, nums2));
    }
}
