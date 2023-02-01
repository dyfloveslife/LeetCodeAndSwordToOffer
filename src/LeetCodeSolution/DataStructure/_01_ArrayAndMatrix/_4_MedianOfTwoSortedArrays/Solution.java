package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._4_MedianOfTwoSortedArrays;

/*
 * 寻找两个正序数组的中位数
 *
 * 题目描述：
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 思路一：使用额外的辅助空间
 * 1. 常规的做法是：先将两个数组合并，然后再排序，根据奇偶进行判断；
 * 2. 当然也可以所用三个指针，逐个比较大小即可；
 * 3. 时间复杂度为 O(m+n)。
 *
 * 思路二：二分
 * 详细解释：
 *   https://leetcode.com/problems/median-of-two-sorted-arrays/solutions/2471/very-concise-o-log-min-m-n-iterative-solution-with-detailed-explanation/
 *   https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/he-bing-yi-hou-zhao-gui-bing-guo-cheng-zhong-zhao-/
 *   https://www.youtube.com/watch?v=ScCg9v921ns
 */
public class Solution {
    // 思路一
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return 0;
        }

        int length1 = nums1.length, length2 = nums2.length;
        int[] nums = new int[length1 + length2];

        // idx、i、j 分别指向 nums、nums1、nums2 的起始位置
        int idx = 0, p1 = 0, p2 = 0;
        while (p1 < length1 && p2 < length2) {
            nums[idx++] = nums1[p1] < nums2[p2] ? nums1[p1++] : nums2[p2++];
        }
        while (p1 < length1) {
            nums[idx++] = nums1[p1++];
        }
        while (p2 < length2) {
            nums[idx++] = nums2[p2++];
        }

        int length = nums.length;
        return (length & 1) == 1 ? nums[length / 2] : (nums[length / 2 - 1] + nums[length / 2]) / 2.0;
    }

    // 思路二
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // 在短的数组上找分割线
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        // m < n
        int m = nums1.length, n = nums2.length;

        // 分割线左边的所有元素需要满足的个数为 m + (n - m + 1) / 2, 该表达式是是为了防止数组长度相加后溢出
        int totalLeft = (m + n + 1) / 2;

        // 在短数组 nums1 的区间 [0, m] 中查找恰当的分割线
        // 使得 nums1[i - 1] <= nums2[j] && nums2[j - 1] <= nums1[i]
        // 也就是需要满足 nums1 中分割线左侧的元素小于等于 nums2 中分割线右侧的元素
        // 同时 nums2 中分割线左侧的元素也要小于等于 nums1 中分割线右侧的元素
        int left = 0, right = m;
        while (left < right) {
            // i、j 指向分割线的位置
            int i = left + (right - left + 1) / 2;
            int j = totalLeft - i;
            if (nums1[i - 1] > nums2[j]) {
                // nums1 中分割线左侧的数多了, 因此分割线要左移, 下一轮的搜索区间为 [left, i - 1]
                right = i - 1;
            } else {
                // 下一轮的搜索区间为 [i, right]
                left = i;
            }
        }

        int i = left, j = totalLeft - i;
        int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
        int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
        int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
        int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];

        if (((m + n) & 1) == 1) {
            return Math.max(nums1LeftMax, nums2LeftMax);
        } else {
            return (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2.0;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};

        System.out.println(solution.findMedianSortedArrays1(nums1, nums2));
        System.out.println(solution.findMedianSortedArrays2(nums1, nums2));
    }
}
