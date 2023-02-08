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
 * 思路：模拟
 * 1. 首先按照每个区间的左端点进行升序排序；
 * 2. 然后先将第一个区间加入到一个容器中，再开始遍历每个区间；
 * 3. 如果当前遍历的区间的左端点大于结果集中的右端点，说明它们之间没有交集，则直接将当前区间加入到结果集中；
 * 4. 如果当前遍历的区间的左端点小于等于结果集中的右端点，说明它们之间有交集，则更新结果集中的右端点的值，也就是取右端点中最大的那个值，即合并操作；
 * 5. 时间复杂度 O(n*log n)，空间复杂度 O(log n)，仅关心排序算法。
 */
public class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return new int[0][];
        }

        Arrays.sort(intervals, (o1, o2) -> (o1[0] - o2[0]));
        List<int[]> ans = new ArrayList<>();
        // 先将第一个区间添加到 ans 中
        ans.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            // 先拿到当前遍历到的区间
            int[] current = intervals[i];
            // 然后再拿到 ans 中最后一个子区间
            int[] last = ans.get(ans.size() - 1);
            // 如果不构成重叠的区间，则将当前遍历到的区间加入到 ans 中
            if (current[0] > last[1]) {
                ans.add(current);
            } else {
                // 扩大重叠区域的有边界
                last[1] = Math.max(last[1], current[1]);
            }
        }

        return ans.toArray(new int[ans.size()][]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] intervals2 = {{1, 4}, {4, 5}};

        System.out.println(Arrays.deepToString(solution.merge(intervals1))); // [[1, 6], [8, 10], [15, 18]]
        System.out.println(Arrays.deepToString(solution.merge(intervals2))); // [[1,5]]
    }
}
