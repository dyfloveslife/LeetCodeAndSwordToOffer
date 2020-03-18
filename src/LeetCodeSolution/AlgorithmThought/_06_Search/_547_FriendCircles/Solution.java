package LeetCodeSolution.AlgorithmThought._06_Search._547_FriendCircles;

/*
 * 朋友圈
 *
 * 题目描述：
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。
 * 如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。
 * 所谓的朋友圈，是指所有朋友的集合。
 *
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。
 * 如果 M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。
 * 你必须输出所有学生中的已知的朋友圈总数。
 *
 * 思路 1：
 * 0. 好友关系可以看成是一个无向图，例如第 0 个人与第 1 个人是好友，那么 M[0][1] 和 M[1][0] 的值都为 1；
 * 1. 使用 DFS，使用 visited 数组，依次判断每个位置，如果当前位置没有被访问过，则朋友圈数量加 1；
 * 2. 然后对该位置进行 dfs 搜索，并标记已经访问过的位置，即将已经访问过的位置置为 true。
 *
 * 思路 2：
 * 1. 自己实现一个并查集。
 */
public class Solution {
    // 定义并查集结构
    static class UF {
        // 节点个数，即记录连通分量的个数
        private int count;
        // 所构成的一棵树，节点 x 的父节点指针指向是 parent[x]
        private int[] parent;
        // 树中节点的数量
        private int[] size;

        // 初始化并查集
        public UF(int n) {
            // 一开始的时候，所有的节点都单独成为一个集合
            this.count = n;
            // 一开始的时候，当前节点的父节点，指向的就是自己
            parent = new int[n];
            // 一开始的时候，所有的节点都单独成为一个集合，此时集合内部的数量就是 1
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // 找到当前节点 x 的代表节点，同时打平（优化）从 x 到 代表节点上的所有节点
        public int findHead(int x) {
            // 从当前节点开始找，一直找到代表节点
            // 最终 x 会带到它的代表节点
            while (parent[x] != x) {
                // 打平沿途经过的节点
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        // 将节点 a 和节点 b 进行合并，
        public void union(int a, int b) {
            int aHead = findHead(a);
            int bHead = findHead(b);
            if (aHead == bHead) {
                return;
            }

            if (size[aHead] > size[bHead]) {
                // 将数量小的 bHead 挂在数量大的 aHead 下面
                parent[bHead] = aHead;
                size[aHead] += size[bHead];
            } else {
                parent[aHead] = bHead;
                size[bHead] += size[aHead];
            }
            count--;
        }

        // 判断两个节点是否是属于同一集合中的
        // 如果它俩的代表节点相同，则说明属于同一集合
        public boolean isSameSet(int a, int b) {
            return findHead(a) == findHead(b);
        }

        // 返回当前的连通分量个数
        public int count() {
            return count;
        }
    }


    // DFS
    public static int findCircleNum(int[][] M) {
        boolean[] visited = new boolean[M.length];
        int res = 0;
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                res++;
                dfs(M, visited, i);
            }
        }
        return res;
    }

    public static void dfs(int[][] M, boolean[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(M, visited, j);
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        System.out.println(findCircleNum(arr)); // 1

        UF uf = new UF(arr.length);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        System.out.println(uf.count);
    }
}
