package LeetCodeSolution.DataStructure._06_Graph._210_CourseScheduleII;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 课程表Ⅱ
 *
 * 题目描述：
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 *
 * 思路：
 * 1. 通过“想要学习课程 0 ，你需要先完成课程 1”这句话可以推测出，可以使用拓扑排序；
 * 2. 拓扑排序用到了 BFS，即每次将入度为 0 的元素放进队列中，然后在原拓扑中将该元素与它的边进行删除；
 * 3. 再次将入度为 0 的元素入队，一直到最后一个元素即可；
 * 4. 可以看到，拓扑排序可以检测有向图中是否存在环。如果在删除掉某个元素后，剩下的元素已经没有入度为 0 的元素了，则说明有环存在；
 * 5. 而无向图中检测是否有环的依据，使用的数据结构是并查集；
 * 6. 除了使用队列来保存入度为 0 的节点外，还需要两个辅助的数据结构：
 *    邻接表：通过节点的索引，能够找到当前节点的后继节点；
 *    入度数组：通过节点的索引，能够找到指向当前节点的节点个数。
 */
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return new int[0];
        }

        // 建立入度表
        int[] inDegrees = new int[numCourses];
        for (int[] p : prerequisites) {
            inDegrees[p[0]]++;
        }

        // 将入度为 0 的节点加入到队列中
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        // 记录可以学完的课程数量
        int count = 0;
        // 可以学完的课程
        int[] res = new int[numCourses];
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            // 将入度为 0 的节点放入结果序列中
            res[count++] = cur;
            for (int[] p : prerequisites) {
                if (p[1] == cur) {
                    // 将当前节点的入度减一
                    inDegrees[p[0]]--;
                    // 检查节点的入度是否为 0，为 0 的话将其加入到队列中
                    if (inDegrees[p[0]] == 0) {
                        queue.offer(p[0]);
                    }
                }
            }
        }
        if (count == numCourses) {
            return res;
        }
        return new int[0];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};

        System.out.println(Arrays.toString(solution.findOrder(numCourses, prerequisites)));
    }
}
