package SwordToOfferSolution._57_02_ContinuousSquenceWithSum;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * 和为 s 的连续正数序列
 *
 * 题目描述：
 * 输入一个正数 s，打印出所有和为 s 的连续正数序列（至少含有两个数）。
 * 例如输入 15，由于 1+2+3+4+5=4+5+6=7+8=15，所以结果打印出 3 个连续序列 1～5、4～6 和 7～8。
 *
 * 思路：
 * 1. 使用两个指针 left 和 right：
 *    1.1) 如果从 left 到 right 的序列的和大于 s，则可以从序列中去掉较小的值，即增大 left 的值；
 *    1.2) 如果从 left 到 right 的序列的和小于 s，则可以从序列中增加较大的值，即增大 right 的值，让这个序列包含更多的数；
 * 2. 注意在求序列和的时候，由于公差是 1，所以可以直接用求和公式：n*(a1+an)/2。
 */
public class Solution {
    public static int[][] findContinuousSequence1(int target) {
        if (target < 3) {
            return new int[0][0];
        }

        int left = 1;
        int right = 2;

        ArrayList<int[]> res = new ArrayList<>();
        while (left < right) {
            int curSum = (right - left + 1) * (left + right) >> 1;
            if (curSum == target) {
                int[] arr = new int[right - left + 1];
                int j = left;
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = j++;
                }
                res.add(arr);
                left++;
            } else if (curSum < target) {
                right++;
            } else {
                left++;
            }
        }

        return res.toArray(new int[0][]);
    }


    public static ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (sum < 3) {
            return res;
        }

        int left = 1;
        int right = 2;
        while (left < right) {
            int curSum = (right - left + 1) * (left + right) >> 1;
            if (curSum == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = left; i <= right; i++) {
                    list.add(i);
                }
                res.add(list);
                left++;
            } else if (curSum < sum) {
                right++;
            } else {
                left++;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(findContinuousSequence1(9)));
    }
}
