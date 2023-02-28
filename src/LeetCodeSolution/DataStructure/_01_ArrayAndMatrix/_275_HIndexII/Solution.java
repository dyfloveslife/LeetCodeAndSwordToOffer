package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._275_HIndexII;

import java.util.Arrays;

/*
 * H 指数 II
 *
 * 题目描述：
 * 给你一个整数数组 citations，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数，其中 citations 已经按照升序排列。计算并返回该研究者的 h 指数。
 * h 指数的定义：h 代表“高引用次数”，一名科研人员的 h 指数是指他（她）的 n 篇论文中总共有 h 篇论文分别被引用了至少 h 次。
 * 且其余的 n - h 篇论文每篇被引用次数不超过 h 次。
 * 如果 h 有多种可能的值，h 指数是其中最大的那个。
 *
 * 思路：
 * 1. 参考 274 题。
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

        int n = citations.length;
        if (citations[n - 1] == 0) {
            return 0;
        }

        int i = 0, j = n - 1;
        while (i < j) {
            int mid = i + ((j - i) >> 1);
            if (citations[mid] < n - mid) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }

        return n - i;
    }

    public static void main(String[] args) {
        int[] nums1 = {0, 1, 3, 5, 6};
        int[] nums2 = {1, 1, 3};
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
