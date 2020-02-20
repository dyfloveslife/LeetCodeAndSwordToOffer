package Other.AdvancedAlgorithm._07_MaxWindow;

import java.util.Arrays;
import java.util.LinkedList;

/*
 * 生成窗口最大值数组
 *
 * 题目描述：
 * 一个整型数组和一个大小为 w 的窗口，从数组的最左边滑动到最右边，窗口每次只能向右滑动一个位置。
 * 实现一个函数，求每次向右滑动一次的情况下所得到的窗口内的最大值，将它们组成一个数组进行返回。
 *
 * 思路：
 * 1. 如果数组长度为 n，窗口大小为 w，则一共可以产生 n-w+1 个最大值；
 * 2. 直接采用滑动窗口解答即可。
 */
public class Solution {

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || arr.length == 0 || w < 1 || arr.length < w) {
            return null;
        }

        LinkedList<Integer> qMax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]) {
                qMax.pollLast();
            }
            // 这里双端队列只存储元素的索引，而判断的时候是判断的元素的大小
            qMax.addLast(i);
            // 由于题目规定，需要将窗口形成 w 大小的时候才进行弹出操作
            // 所以，等到 w 扩到 w+1 位置的时候，再进行下面的判断，
            // 判断队列头的元素是否过期
            if (qMax.peekFirst() == (i - w)) {
                qMax.pollFirst();
            }
            // 每次将队列头部的元素收集到 res 中即可
            if (i >= (w - 1)) {
                res[index++] = arr[qMax.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        System.out.println(Arrays.toString(getMaxWindow(arr, 3)));
    }
}