package LeetCodeSolution.AlgorithmThought._04_BinarySearch._1095_FindInMountainArray;

/*
 * 山脉数组中查找目标值
 *
 * 题目描述：
 * 给你一个 山脉数组 mountainArray，请你返回能够使得 mountainArray.get(index) 等于 target 最小 的下标 index 值。
 * 如果不存在这样的下标 index，就请返回 -1。
 *
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 *
 * 山脉数组 A 的定义：
 * 1. A.length >= 3
 * 2. 在 0 < i < A.length - 1 条件下，存在 i 使得：
 *   A[0] < A[1] < ... A[i-1] < A[i]
 *   A[i] > A[i+1] > ... > A[A.length - 1]
 *
 * 思路：
 * 1. 使用 3 次二分即可，首先找到山脉数组的山顶，山顶左边都是升序的元素，山顶右边都是降序的元素；
 * 2. 分别对数组两边使用二分，以此来找到 target。
 */

interface MountainArray {
    int get(int index);

    int length();
}

public class Solution {
    public int findInMountainArray(int target, MountainArray mountainArray) {
        int len = mountainArray.length();
        int mountainTop = findMountainTop(mountainArray, 0, len - 1);
        int res = findTargetInBefore(mountainArray, 0, mountainTop, target);
        if (res != -1) {
            return res;
        }
        return findTargetInAfter(mountainArray, mountainTop + 1, len - 1, target);
    }

    /**
     * 返回数组山顶的索引
     *
     * @param mountainArray
     * @param left
     * @param right
     * @return
     */
    public int findMountainTop(MountainArray mountainArray, int left, int right) {
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            if (mountainArray.get(middle) < mountainArray.get(middle + 1)) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }

    /**
     * 在升序的 [0, mountainTop] 区间内找 target
     *
     * @param mountainArray
     * @param left
     * @param right
     * @param target
     * @return
     */
    public int findTargetInBefore(MountainArray mountainArray, int left, int right, int target) {
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            if (mountainArray.get(middle) < target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        if (mountainArray.get(left) == target) {
            return left;
        }
        return -1;
    }

    /**
     * 在降序的 [mountainTop, len - 1] 区间内找 target
     *
     * @param mountainArray
     * @param left
     * @param right
     * @param target
     * @return
     */
    public int findTargetInAfter(MountainArray mountainArray, int left, int right, int target) {
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            if (mountainArray.get(middle) > target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        if (mountainArray.get(left) == target) {
            return left;
        }
        return -1;
    }
}
