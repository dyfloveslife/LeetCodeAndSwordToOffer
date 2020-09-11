package LeetCodeSolution.DataStructure._06_Graph._785_IsGraphBipartite;

/*
 * 判断二分图
 *
 * 题目描述：
 * 给定一个无向图 graph，当这个图为二分图时返回 true。
 * 如果我们能将一个图的节点集合分割成两个独立的子集 A 和 B，并使图中的每一条边的两个节点一个来自 A 集合，一个来自 B 集合，我们就将这个图称为二分图。
 *
 * 思路：
 * 1. 按照题目的要求可知：一条边所连接的两个点不能属于同一个集合，
 * 2. 因此，当我们遍历到某个节点后，就将其染成颜色，然后将与它相连的其他顶点染成另外一种颜色，
 * 3. 但是，如果采用 bfs 去找其他相邻的点的话，如果是连通图，则可以遍历完全部的节点，
 * 4. 而如不是连通图，那么就需要遍历每个节点，然后通过每个节点去找附近的节点，
 * 5. 对于重复的情况，我们可以使用一个标志位，记录当前遍历到的节点是否已经染过色；
 * 6. 当然，也可以使用 dfs 去遍历。
 */
public class Solution {
    // 分别使用无色、红色、绿色进行涂色
    private static final int UNCOLORED = 0;
    private static final int RED = 1;
    private static final int GREEN = 2;
    // 用于记录节点的颜色
    private int[] color;
    // 判断是否为二分图
    private boolean isValid = true;

    public boolean isBipartite(int[][] graph) {
        // 图中节点的数量
        int n = graph.length;
        color = new int[n];
        // 由于题目中没有说明一定是连通图，因此需要将每个没有涂色的节点作为起始节点进行涂色操作，
        // 当然，一开始的时候，将没有涂色的节点染成红色，然后会把它附近的节点染成绿色
        for (int i = 0; i < n; i++) {
            if (color[i] == UNCOLORED) {
                dfs(i, RED, graph);
            }
        }
        return isValid;
    }

    private void dfs(int node, int curColor, int[][] graph) {
        color[node] = curColor;
        // 给当前节点的附近节点进行涂色，
        // 如果这个附近的节点已经是红色了，那么保持原来的红色，
        // 如果这个附近的节点不是红色，那么久将其染成绿色
        int newColor = curColor == RED ? GREEN : RED;
        // 遍历当前节点相邻的其它节点
        for (int neighbor : graph[node]) {
            // 如果附近节点还没有涂色，那么继续 dfs 进行涂色
            if (color[neighbor] == UNCOLORED) {
                dfs(neighbor, newColor, graph);
                // 在涂色的过程中，如果已经不是二分图了，则直接返回
                if (!isValid) {
                    return;
                }
                // 如果相邻节点已经颜色了，并且与本次遍历本应该涂的颜色不一致，则说明不是二分图
            } else if (color[neighbor] != newColor) {
                isValid = false;
                return;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] graph = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};

        System.out.println(solution.isBipartite(graph));
    }
}
