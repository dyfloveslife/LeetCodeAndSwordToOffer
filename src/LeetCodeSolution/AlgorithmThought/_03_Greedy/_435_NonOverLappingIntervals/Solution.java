package LeetCodeSolution.AlgorithmThought._03_Greedy._435_NonOverLappingIntervals;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 无重叠区间
 *
 * 题目描述：
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 * 思路：
 * 1. 属于求解时间区间的最大不相交子集问题；
 * 2. https://leetcode-cn.com/problems/non-overlapping-intervals/solution/tan-xin-suan-fa-zhi-qu-jian-diao-du-wen-ti-by-labu/
 * 3. 看哪个项目结束的时间早，即 end 最小的；
 * 4. 选择最早结束的项目 x ，然后淘汰掉因为该项目而不能做的项目，即将与 x 相交的区间删除；
 * 5. 然后再看哪个项目早结束，直至最后，选出的哪些 x 就是可以安排的区间，即最大不相交子集；
 * 6. 在实现算法的时候，可以按每个区间的 end 进行升序排序；
 * 7. 如何去除与 x 相交的区间？
 *    由于已经排好序了，所以所有与 x 相交的区间必然会与 x 的 end 相交；
 *    如果一个区间不与 x 的 end 相交，它的 start 必须大于或等于 x 的 end。
 */
public class Solution {
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // 按照 end 进行升序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        // 至少有一个区间不相交
        int count = 1;
        // 排序后，第一个区间就是 x，即先取到第一个区间的 end
        int x_end = intervals[0][1];
        for (int[] interval : intervals) {
            // 取到每个一维数组的 start
            int start = interval[0];
            // 某个区间不与 x 相交
            if (x_end <= start) {
                // 寻找下一个区间
                count++;
                // 来到当前 interval 的 end
                x_end = interval[1];
            }
        }
        // return count;
        return intervals.length - count;
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        int[][] arr1 = {{1, 2}, {1, 2}, {1, 2}};
        int[][] arr2 = {{1, 2}, {2, 3}};
        System.out.println(eraseOverlapIntervals(arr));
    }
}
