package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._253_MeetingRoomsII;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * 会议室 Ⅱ
 *
 * 题目描述：
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，
 * 为了避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
 *
 * 思路：
 * 1. 使用小顶堆；
 * 2. 首先按照每个会议的开始时间进行排序；
 * 3. 创建一个小顶堆，堆顶元素填入第一个会议的结束时间；（堆中存储的是会议室的结束时间）
 * 4. 依次遍历每个会议，检查堆顶的会议室是否空闲，也就是检查当前遍历到的会议的开始时间是否大于等于堆顶会议的结束时间：
 *    如果空闲，说明不能充分利用会议室资源，则弹出堆顶元素，并加入当前会议；
 *    如果不空闲，即当前遍历到的会议的开始时间小于堆顶会议的结束时间，则说明可以充分这两个会议室的资源，然后将当前会议室的结束时间加入到堆中；
 * 5. 最后返回堆的个数，就是会议室的数量。
 */
public class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (o1, o2) -> (o1[0] - o2[0]));
        Queue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.offer(intervals[i][1]);
        }

        return minHeap.size();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
        int[][] intervals2 = {{13, 15}, {1, 13}};

        System.out.println(solution.minMeetingRooms(intervals1)); // 2
        System.out.println(solution.minMeetingRooms(intervals2)); // 1
    }
}
