package SwordToOfferSolution._57_02_ContinuousSquenceWithSum;

import jdk.nashorn.internal.ir.LiteralNode;

import java.util.ArrayList;

/*
 * 和为 s 的连续正数序列
 *
 * 思路：
 * 1. 使用两个指针 left 和 right：
 *     如果从 left 到 right 的序列的和大于 s，则可以从序列中去掉较小的值，即增大 left 的值；
 *     如果从 left 到 right 的序列的和小于 s，则可以从序列中去掉较大的值，即减小 right 的值，让这个序列包含更多的数；
 *
 */
public class Solution {
    public ArrayList<ArrayList<Integer>> findContinuousSquence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (sum < 3) {
            return res;
        }

        int left = 1;
        int right = 2;
        int curSum = 3;

        while (right < sum) {
            if (curSum > sum) {
                curSum -= left;
                left++;
            } else if (curSum < sum) {
                right--;
                curSum += right;
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = left; i < right; i++) {
                    list.add(i);
                }
                res.add(list);
                curSum -= left;
                left++;
                right++;
                curSum += right;
            }
        }
        return res;
    }
}
