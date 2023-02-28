package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._274_HIndex;

import java.util.Arrays;

/*
 * H 指数
 *
 * 题目描述：
 * 给你一个整数数组 citations，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。计算并返回该研究者的 h 指数。
 * h 指数的定义：h 代表“高引用次数”，一名科研人员的 h 指数是指他（她）的 n 篇论文中总共有 h 篇论文分别被引用了至少 h 次。
 * 且其余的 n - h 篇论文每篇被引用次数不超过 h 次。
 * 如果 h 有多种可能的值，h 指数是其中最大的那个。
 *
 * 思路一：排序
 * 1. 对给定引用次数数组排序后，从大到小遍历数组；
 * 2. 初始化 h 为 0，如果 H 指数为 h 并且在遍历的过程中当前值 citations[i] > h，则说明找到了一篇被引用了至少 h + 1 次的论文，将 h++；
 * 3. 直到 h 无法继续增大，最终返回 h 即可；
 * 4. 时间复杂度为 O(n log n)，空间复杂度为 O(log n)。
 *
 * 思路二：排序 + 二分
 * 1. 题目要求返回论文数量；
 * 2. 对于 [1 3 | 5 7 10 13]，可以观察到分割线右边的最少引用次数(5) >= 分割线右边的论文数量(4)；
 * 3. 对于 [0 | 1 3 5 6]，可以看到分割线右边的最少引用次数(1) < 分割线右边的论文数量(4)，猜测有 4 篇论文至少被引用 4 次，
 *    但是分割线右边的最小引用次数才为 1，说明分割线需要往右移动；其它情况需要往左移动。
 */
public class Solution {
    // 思路一
    public int hIndex1(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }

        Arrays.sort(citations);
        int h = 0, i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }

        return h;
    }

    // 思路二
    public int hIndex2(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }

        Arrays.sort(citations);
        int n = citations.length;
        // 如果全部文章的引用次数都为 0, 则 h 指数为 0
        if (citations[n - 1] == 0) {
            return 0;
        }

        int i = 0, j = n - 1;
        while (i < j) {
            int mid = i + ((j - i) >> 1);
            // 分割线右边的最少引用次数 < 分割线右边的论文数量
            // citations[mid] 表示猜测的次数
            // n - mid 表示分割线右边的文章数量
            if (citations[mid] < n - mid) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }

        // 退出循环以后, mid 就来到了合适的位置
        // 题目要返回的是论文篇数
        return n - i;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 0, 6, 1, 5};
        int[] nums2 = {1, 3, 1};
        int[] nums3 = {0};
        int[] nums4 = {0, 1};

        Solution solution = new Solution();
        System.out.println(solution.hIndex1(nums1)); // 3
        System.out.println(solution.hIndex1(nums2)); // 1
        System.out.println(solution.hIndex1(nums3)); // 0
        System.out.println(solution.hIndex1(nums4)); // 1

        System.out.println("--");
        System.out.println(solution.hIndex2(nums1)); // 3
        System.out.println(solution.hIndex2(nums2)); // 1
        System.out.println(solution.hIndex2(nums3)); // 0
        System.out.println(solution.hIndex2(nums4)); // 1
    }
}
