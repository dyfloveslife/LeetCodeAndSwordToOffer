package Other.AdvancedAlgorithm._08_AllLessNumSubArray;

import java.util.LinkedList;

/*
 * 最大值减去最小值小于等于 num 的子数组数量
 *
 * 题目描述：
 * 给定数组 arr 和整数 num，返回共有多少个子数组满足以下情况:
 * max(arr[i...j]) - min(arr[i...j]) <= num
 * max(arr[i...j]) 表示子数组 arr[i...j] 中的最大值，min(arr[i...j]) 表示子数组 arr[i...j] 中的最小值。
 *
 * 例如：给定数组 [1, 2, 5] 以及整数 1。
 * 所有的子数组为：[1]、[1, 2]、[1, 2, 5]、[2]、[2, 5]、[5]；
 * 满足条件的子数组为：[1]、[2]、[1, 2]、[5]，即最终结果输出 4。
 *
 * 思路：
 * 使用两个滑动窗口，一个求最大，另一个求最小。
 */
public class Solution {
    // 暴力 O(N^3)
    public static int getNum1(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int res = 0;
        for (int start = 0; start < arr.length; start++) {
            for (int end = start; end < arr.length; end++) {
                if (isValid(arr, start, end, num)) {
                    System.out.println("[" + arr[start] + ", " + arr[end] + "]");
                    res++;
                }
            }
        }
        return res;
    }

    public static boolean isValid(int[] arr, int start, int end, int num) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        return max - min <= num;
    }

    // O(N)
    public static int getNum2(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        LinkedList<Integer> qMin = new LinkedList<>();
        LinkedList<Integer> qMax = new LinkedList<>();
        int i = 0;
        int j = 0;
        int res = 0;
        while (i < arr.length) {
            // 当 j 确定位置的时候，j 往右扩不能再扩的时候就停
            while (j < arr.length) {
                // 最小值结构更新，然后将 j 加进去
                while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[j]) {
                    qMin.pollLast();
                }
                qMin.addLast(j);
                // 最大值结构更新，然后将 i 加进去
                while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[j]) {
                    qMax.pollLast();
                }
                qMax.addLast(j);
                // 不达标的情况
                if (arr[qMax.getFirst()] - arr[qMin.getFirst()] > num) {
                    break;
                }
                j++;
            }
            // 查看下标是否过期
            if (qMin.peekFirst() == i) {
                qMin.pollFirst();
            }
            if (qMax.peekFirst() == i) {
                qMax.pollFirst();
            }
            res += j - i;
            // 换一个开头
            i++;
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 5};
        System.out.println(getNum1(arr, 1));
    }
}