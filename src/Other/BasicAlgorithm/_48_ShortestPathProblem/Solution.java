package Other.BasicAlgorithm._48_ShortestPathProblem;

import java.util.*;

/*
 * 最短路径问题
 *
 * Leetcode 743
 * 详见博客：
 */
public class Solution {
    // Dijkstr(堆优化 + 邻接表)
    // 矩阵 times 表示两个顶点以及它们之间权值信息，times 中的数组 a[0] 表示源顶点，a[1] 表示目标顶点，a[2] 表示权值，单向的
    // N 表示图中顶点的数量，K 表示初始顶点
    // 邻接表使用 map<Integer, List<int[]>> 来存放源顶点到目标顶点以及权值
    // 设置 dis 数组和 vis 数组，都开 N + 1 大小，需要注意的是，初始化 dis 时，设置 -1 还是 INF，这需要看情况
    // 不要忘记初始化之后再重置 dis[源] 和 dis[0] 为 0
    public int dijkstr(int[][] times, int N, int K) {
        /*
        // 邻接矩阵的初始化方式如下，用于存放各个顶点之间的权值（距离）
        int[][] graph = new int[N + 1][N + 1];
        for (int[] row : graph) {
            Arrays.fill(row, -1);
        }
        for (int[] t : times) {
            graph[t[0]][t[1]] = t[2];
        }
         */

        HashMap<Integer, List<int[]>> map = new HashMap<>();
        // 初始化邻接表，并存放各个顶点之间的权值
        for (int[] t : times) {
            map.computeIfAbsent(t[0], k -> new ArrayList<>()).add(new int[]{t[1], t[2]});
        }

        // 定义无穷大 INF，为什么不用 0x7fffffff？
        // 因为 0x7fffffff 表示 32 bit int 类型的最大值，在最短路径算法中，如果使用松弛操作，会产生溢出而变成一个很小的负数。
        // 因此，0x7fffffff 不能满足“无穷大加一个无穷大依然是无穷大”这个条件，
        // 而 0x3f3f3f3f 可以，它的十进制是 1061109567，是 10 的 9 次方级别的，它满足上面的这个条件。
        // 当然，具体初始化成 -1 还是 INF，还是要看题目要求。
        int INF = 0x3f3f3f3f;
        // 初始化 distance 数组，存放顶点 K 到各个顶点的最短路径，最大的那个就是最终返回的结果，不过在返回之前需要处理一下
        int[] dis = new int[N + 1];
        Arrays.fill(dis, INF);
        boolean[] vis = new boolean[N + 1];

        // K 到 K 的距离为 0，由于使用了 stream，所以 dis[0] 也要初始化为 0
        dis[K] = 0;
        dis[0] = 0;

        // 创建小根堆，按照 dis 升序排序，可以省去松弛操作
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(((o1, o2) -> (dis[o1] - dis[o2])));
        minHeap.offer(K);

        while (!minHeap.isEmpty()) {
            // 拿出一个源顶点出来
            Integer poll = minHeap.poll();
            // 如果已经访问过了，则跳过
            if (vis[poll]) continue;
            // 如果没有访问过，则首先标记已被访问
            vis[poll] = true;
            // 遍历它的邻居顶点，如果没有邻居顶点的话，返回返回空 list，显然使用 getOrDefault 处理更加方便
            List<int[]> list = map.getOrDefault(poll, Collections.emptyList());
            for (int[] arr : list) {
                // 拿出一个邻居顶点
                int next = arr[0];
                // 如果该邻居顶点已经访问过了，则跳过
                if (vis[next]) continue;
                // 更新到这个邻居的最短距离，看看是不是当前 poll 出来的节点到它更加仅一些
                dis[next] = Math.min(dis[next], dis[poll] + arr[1]);
                minHeap.offer(next);
            }
        }
        int ans = Arrays.stream(dis).max().getAsInt();
        return ans == INF ? -1 : ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int N = 4;
        int K = 2;

        System.out.println(solution.dijkstr(times, N, K));
    }
}
