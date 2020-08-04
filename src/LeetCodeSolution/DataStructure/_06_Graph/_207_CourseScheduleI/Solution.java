package LeetCodeSolution.DataStructure._06_Graph._207_CourseScheduleI;

import java.util.*;

/*
 * 课程表
 *
 * 题目描述：
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0, 1]
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 * 思路：
 * 0. https://www.youtube.com/watch?v=fskPWs3Nuhc
 * 1. 问“是否可能完成所有课程的学习？”的意思就是判断有向图中是否存在环，也就是让我们判断是否是有向无环图；
 * 2. 这里可以使用拓扑排序来判断是否含有环，如果在剔除某些节点和边后，没有入度为 0 的节点，则说明存在环；
 * 3. 由于每个课程之间规定的前置条件，因此不能构成任何环路，否则前置条件不成立；
 *
 * 扩展：
 * 1. 本题属于图中的拓扑排序问题，因此有一个模板可以使用；
 * 2. 构建邻接表和入度数组->将入度为 0 的入队列->广度遍历
 */
public class Solution {
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
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

        int count = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            count++;
            for (int[] p : prerequisites) {
                if (p[1] == cur) {
                    inDegree[p[0]]--;
                    if (inDegree[p[0]] == 0) {
                        queue.offer(p[0]);
                    }
                }
            }
        }
        return count == numCourses;
    }

    // https://i.loli.net/2020/08/04/gDEndl6AM8QGSYo.png
    // 整体的流程是：使用 map<Integer, List<Integer>> 构造邻接表，将入度为 0 的节点作为 key，将该节点所指向的节点存在 value 的 list 中；
    // 对于给定的数组 [4, 0]，其中索引为 0 的位置表示 toTake，而索引为 1 的位置表示先行课程，也就是 prerequisite；
    // 构造一个队列，首先将入度为 0 的节点全部加入到队列中；
    // 每次从队列中弹出一个节点，通过这个节点进而得到它对应的 value，也就是它所指向的节点，也就是 list；
    // 遍历这个 list，将 list 中节点的入度数量减一，然后，如果此时入度为 0 了，那么再将该节点入队；
    // 最后再遍历一遍入度数组 inDegree，只要有一个元素不为 0，则说明存在环，则就返回 false。
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][0]]++;
            if (graph.containsKey(prerequisites[i][1])) {
                graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(prerequisites[i][0]);
                graph.put(prerequisites[i][1], list);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            count++;
            List<Integer> toTake = graph.get(cur);
            for (int i = 0; toTake != null && i < toTake.size(); i++) {
                inDegree[toTake.get(i)]--;
                if (inDegree[toTake.get(i)] == 0) {
                    queue.offer(toTake.get(i));
                }
            }
        }
        return count == numCourses;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}, {0, 1}};

        System.out.println(solution.canFinish1(numCourses, prerequisites));
        System.out.println(solution.canFinish2(numCourses, prerequisites));
    }
}
