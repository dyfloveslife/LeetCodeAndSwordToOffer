package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._56_MergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 合并区间
 *
 * 题目描述：
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 思路：
 * 1. 首先按照每个区间的左端点进行升序排序；
 * 2. 然后先将第一个区间加入到一个容器中，再开始遍历每个区间；
 * 3. 如果当前遍历的区间的左端点大于结果集中的右端点，说明它们之间没有交集，则直接将当前区间加入到结果集中；
 * 4. 如果当前遍历的区间的左端点小于等于结果集中的右端点，说明它们之间有交集，则更新结果集中的右端点的值，
 *    即进行合并操作，也就是取右端点中最大的那个值
 */
public class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return new int[0][];
        }

        Arrays.sort(intervals, (o1, o2) -> (o1[0] - o2[0]));
        List<int[]> res = new ArrayList<>();
        // 先将第一个区间添加到 res 中
        res.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            // 先拿到当前遍历到的区间
            int[] interval = intervals[i];
            // 然后再拿到之前每次添加到 res 中的最后一个子数组
            int[] pre = res.get(res.size() - 1);
            // 如果不构成重叠的区间，则将当前遍历到的区间加入到 res 中
            if (pre[1] < interval[0]) {
                res.add(interval);
            } else {
                // 扩大重叠区域的有边界
                pre[1] = Math.max(pre[1], interval[1]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        System.out.println(Arrays.deepToString(solution.merge(intervals)));
    }
}
