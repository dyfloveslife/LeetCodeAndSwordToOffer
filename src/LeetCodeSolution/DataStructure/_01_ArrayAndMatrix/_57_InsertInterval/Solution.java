package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._57_InsertInterval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 插入区间
 *
 * 题目描述：
 * 给你一个无重叠的、按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * 分析：
 * 1. 对于区间 s1=(a1, b1) 和 s2=(a2, b2)，如果它们有公共区间，那么公共的区间为 (max(a1, a2), min(b1, b2))；
 * 2. 如果 (a1, b1) 和 (a2, b2) 存在重复区间，那么合并后，最终的区间应该是 (min(a1, a2), max(b1, b2))；
 * 3. 如果它们之间没有重叠，说明要么 s1 在 s2 左侧，即 b1 < a2，要么 s1 在 s2 右侧，即 b2 < a1；
 * 4. 如果都不满足 3 中的情况，则说明 s1 和 s2 必定有重叠，因此可以计算交集和并集。
 *
 * 思路：模拟
 * 1. 若给定的集合为 X，新区间为 S=[left, right]，那么有如下步骤：
 * 2. 找出所有与新区间 S 重叠的子区间 Xi；
 * 3. 将 Xi 中所有区间与 S 合并成一个大区间；
 * 4. 最终的结果就是合并后的大区间以及不与 Xi 重叠的区间；
 * 5. 需要注意的是：因为答案需要满足按照左端点排序，因此如果第一次遇到满足某个 interval[0] > right 时，说明以后遍历到的其他区间都不会再与 S 重叠；
 * 6. 而且其他区间的左端点一定是大于 left 的，此时可以将 S 加入 ans；
 * 7. 如果不存在这样的区间，则需要将 S 加入 ans。
 */
public class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null) {
            return new int[0][];
        }

        List<int[]> ans = new ArrayList<>();
        // 用于标识 newInterval 是否已经被放置了
        boolean placed = false;
        int left = newInterval[0], right = newInterval[1];
        // 遍历每个 interval，判断当前 interval 位于 newInterval 的左侧、右侧还是存在交集
        for (int[] interval : intervals) {
            // interval 在 newInterval 的右侧且不相交
            if (right < interval[0]) {
                // 如果 newInterval 没有被放置过
                if (!placed) {
                    //ans.add(newInterval);
                    ans.add(new int[]{left, right});
                    placed = true;
                }
                ans.add(interval);
                // interval 在 newInterval 左侧且不相交
            } else if (interval[1] < left) {
                ans.add(interval);
            } else {
                // interval 与 newInterval 存在交集，则进行合并
                // 注意：合并的意思其实是不断扩大 newInterval 的左右区间
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ans.add(new int[]{left, right});
        }

        return ans.toArray(new int[ans.size()][]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newIntervals1 = {2, 5};
        System.out.println(Arrays.deepToString(solution.insert(intervals1, newIntervals1))); // [[1, 5], [6, 9]]

        int[][] intervals2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newIntervals2 = {4, 8};
        System.out.println(Arrays.deepToString(solution.insert(intervals2, newIntervals2))); // [[1, 2], [3, 10], [12, 16]]

        int[][] intervals3 = {};
        int[] newIntervals3 = {5, 7};
        System.out.println(Arrays.deepToString(solution.insert(intervals3, newIntervals3))); // [[5, 7]]

        int[][] intervals4 = {{1, 5}};
        int[] newIntervals4 = {2, 3};
        System.out.println(Arrays.deepToString(solution.insert(intervals4, newIntervals4))); // [[1, 5]]

        int[][] intervals5 = {{1, 5}};
        int[] newIntervals5 = {2, 7};
        System.out.println(Arrays.deepToString(solution.insert(intervals5, newIntervals5))); // [[1, 7]]
    }
}
