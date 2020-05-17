package LeetCodeSolution.DataStructure._06_Graph._207_CourseScheduleI;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 课程表
 *
 * 题目描述：
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 * 思路：
 * 1. 问“是否可能完成所有课程的学习？”的意思就是判断有向图中是否存在环；
 * 2. 这里可以使用拓扑排序来判断是否含有环，如果在剔除某些节点和边后，没有入度为 0 的节点，则说明存在环。
 */
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return false;
        }

        // 定义入度表
        int[] inDegree = new int[numCourses];
        for (int[] p : prerequisites) {
            inDegree[p[0]]++;
        }

        // 入度为 0 的节点队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int res = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res++;
            for (int[] p : prerequisites) {
                if (p[1] == cur) {
                    inDegree[p[0]]--;
                    if (inDegree[p[0]] == 0) {
                        queue.offer(p[0]);
                    }
                }
            }
        }
        return res == numCourses;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}, {0, 1}};

        System.out.println(solution.canFinish(numCourses, prerequisites));
    }
}
