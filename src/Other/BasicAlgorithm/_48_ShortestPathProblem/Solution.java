package Other.BasicAlgorithm._48_ShortestPathProblem;

import java.util.*;

/*
 * 最短路径问题
 *
 * Leetcode 743
 * 详见博客：https://dyfloveslife.github.io/2020/08/26/alg-Shortest-Path-Problem/
 */
public class Solution {
    // Dijkstr(堆优化 + 邻接表)
    // 矩阵 times 表示两个顶点以及它们之间权值信息，times 中的数组 a[0] 表示源顶点，a[1] 表示目标顶点，a[2] 表示权值，单向的
    // N 表示图中顶点的数量，K 表示初始顶点
    // 邻接表使用 map<Integer, List<int[]>> 来存放源顶点到目标顶点以及权值
    // 例如给定的源顶点、目标顶点、源顶点到目标顶点之间的权值，如下所示：
    // [[2, 1, 1],
    //  [2, 3, 1],
    //  [3, 4, 1]]
    // HashMpa 中对应的结构为：
    // 2 : List[[1, 1], [3, 1]] 表示源顶点 2 到目标顶点 1 的权值为 1，同时，源顶点 2 到目标顶点 3 的权值为 1。
    // 3 : List[[4, 1]] 表示源顶点 3 到目标顶点 4 的权值为 1。
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

    // SPFA 算法
    // 队列优化 + 邻接表
    // dis 和 vis 数组也要开 N+1，初始化也要看题干
    // 算法思想：
    // 1. 初始时，只将起始顶点放进队列中；
    // 2. 遍历与起点相连的边，如果可以松弛，则更新 dis[]，然后如果这个顶点没有在队列中，就将其入队并标记；
    // 3. 出队首元素，取消标记，循环 2~3，直至队列为空；
    // 4. 所有可能更新的顶点都更新完毕，dis[] 数组中的距离就是起始顶点到其它顶点的最短距离
    public int spfa1(int[][] times, int N, int K) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        // 构建邻接表
        for (int[] arr : times) {
            List<int[]> list = map.getOrDefault(arr[0], new ArrayList<>());
            list.add(new int[]{arr[1], arr[2]});
            map.put(arr[0], list);
        }

        int INF = 0x3f3f3f3f;
        // 初始化 dis 数组和 vis 数组
        int[] dis = new int[N + 1];
        Arrays.fill(dis, INF);
        boolean[] vis = new boolean[N + 1];
        dis[K] = dis[0] = 0;

        Queue<Integer> queue = new LinkedList<>();
        // 初始顶点先入队
        queue.offer(K);

        while (!queue.isEmpty()) {
            // 取队首顶点
            Integer poll = queue.poll();
            // 可重复入队
            vis[poll] = false;
            // 遍历源点的邻居，更新距离
            List<int[]> list = map.getOrDefault(poll, Collections.emptyList());
            for (int[] arr : list) {
                int next = arr[0];
                // 如果没更新过，或者需要更新距离
                if (dis[next] == INF || dis[next] > dis[poll] + arr[1]) {
                    // 则更新距离
                    dis[next] = dis[poll] + arr[1];
                    // 如果队伍中没有，就不需要再次入队了
                    if (!vis[next]) {
                        vis[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }
        int ans = Arrays.stream(dis).max().getAsInt();
        return ans == INF ? -1 : ans;
    }

    // 邻接矩阵写法
    public int spfa2(int[][] times, int N, int K) {
        int[][] graph = new int[N + 1][N + 1];
        // 初始化 graph
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                graph[i][j] = (i == j) ? 0 : -1;
            }
        }
        // 单向边
        for (int[] t : times) {
            graph[t[0]][t[1]] = t[2];
        }
        int INF = 0x3f3f3f3f;
        // 初始化 dis 和 vis
        int[] dis = new int[N + 1];
        Arrays.fill(dis, INF);
        boolean[] vis = new boolean[N + 1];
        dis[K] = dis[0] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(K);
        vis[K] = true;

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            vis[poll] = false;
            for (int next = 1; next <= N; next++) {
                int w = graph[poll][next];
                // 如果是邻居，并且需要更新，才能进行更新
                if (w != -1 && (dis[next] == INF || dis[next] > dis[poll] + w)) {
                    dis[next] = dis[poll] + w;
                    // 如果队列中没有，也就是没有被访问过，那么就可以入队，而不要重复入队
                    if (!vis[next]) {
                        vis[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }
        int ans = Arrays.stream(dis).max().getAsInt();
        return ans == INF ? -1 : ans;
    }

    // Floyd
    // 由于 Floyd 的本质是动态规划，所以这里使用邻接矩阵
    // 它不使用 dis 和 vis 数组
    // 注意：在初始化邻接矩阵的时候，如果两个顶点没有边，最好初始化为 INF，不要初始化为 -1
    private static final int INF = 0x3f3f3f3f;

    public int floyd(int[][] times, int N, int K) {
        int[][] graph = new int[N + 1][N + 1];
        // 一开始的时候，距离被初始化为 INF，而不是像 SPFA 初始化为 -1
        // SPFA 初始化为 -1 只是为了判断是否为邻居，这里初始化为 INF 是因为要取 min
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                graph[i][j] = (i == j) ? 0 : INF;
            }
        }
        for (int[] t : times) {
            graph[t[0]][t[1]] = t[2];
        }
        // 使用 k 节点来松弛 i 到 j 的最短路径，也就是说，利用 k 作为中间节点
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    // 判断语句可以不用写，具体得看题目
                    // if (k != i && k != j && graph[i][k] != INF && graph[j][k] != INF) {
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    // }
                }
            }
        }
        // graph[a][b] 表示 a 到 b 的最短距离
        int ans = 0;
        for (int distance : graph[K]) {
            ans = Math.max(ans, distance);
        }
        return ans == INF ? -1 : ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int N = 4;
        int K = 2;

        System.out.println(solution.dijkstr(times, N, K));
        System.out.println(solution.spfa1(times, N, K));
        System.out.println(solution.spfa2(times, N, K));
        System.out.println(solution.floyd(times, N, K));
    }
}
