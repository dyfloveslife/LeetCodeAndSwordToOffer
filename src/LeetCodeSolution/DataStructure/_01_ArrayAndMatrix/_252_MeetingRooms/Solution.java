package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._252_MeetingRooms;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 会议室
 *
 * 题目描述：
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，请你判断一个人是否能够参加这里面的全部会议。
 *
 * 思路：
 * 1. 每个子数组按照左边界元素排序，判断当前数组的右边界元素是否大于下一个数组的左边界元素；
 * 2. 若成立，则会议时间存在重合，因此无法参加全部的会议。
 */
public class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return false;
        }

        //Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 0; i + 1 < intervals.length; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
        int[][] intervals2 = {{7, 10}, {2, 4}};

        Solution solution = new Solution();
        System.out.println(solution.canAttendMeetings(intervals1)); // false
        System.out.println(solution.canAttendMeetings(intervals2)); // true
    }
}
