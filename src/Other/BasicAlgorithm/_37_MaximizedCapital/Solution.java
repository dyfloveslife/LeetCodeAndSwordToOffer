package Other.BasicAlgorithm._37_MaximizedCapital;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * 项目的最大钱数
 *
 * 题目描述：
 * 有一个成本数组 c 和一个收益数组 p，共有 i 个项目。则 c[i] 表示第 i 个项目的成本，p[i] 表示
 * 第 i 个项目的利润，即在已经扣除成本之后还能挣到的钱，k 表示在串行做项目的情况下最多能做的项目个数，m 表示项目的初始资金。
 *
 * 求最后获得的最大钱数。
 *
 * 思路：
 * 1. 首先初始化每个项目，然后开一个小根堆，小根堆存储每个项目的成本，成本低的在顶部；
 * 2. 依次弹出小根堆的头部，只要成本比初始资金（w）小，则全部弹出来，然后放到大根堆里；
 * 3. 此时，对于小根堆来说，所有可以做（启动）的项目就进了大根堆。而没有弹出的项目是当前做不了的项目；
 * 4. 大根堆存储每个项目的收益，收益高的在顶部。此时大根堆里存储的就是可以做（启动）的项目；
 * 5. 然后从大根堆中弹出一个项目，则此项目就是所有解锁的项目中收益最高的；
 * 6. 然后做完一个项目后，初始资金就会增加，再看小根堆中有哪些项目可以被解锁，即看哪些项目可以启动；
 * 7. 一直这样做 k 个项目，直至结束。
 */
public class Solution {

    public static class Node {
        // 成本
        int c;
        // 利润
        int p;

        Node(int c, int p) {
            this.c = c;
            this.p = p;
        }
    }

    // 小根堆，存储成本
    public static class MinCostComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }

    // 大根堆，存储利润
    public static class MaxProfitComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }
    }

    public static int findMaximizedCapital(int[] Costs, int[] Profits, int k, int w) {
        // 生成每个项目，每个项目包括各自的成本和利润
        Node[] nodes = new Node[Profits.length];
        for (int i = 0; i < Profits.length; i++) {
            nodes[i] = new Node(Costs[i], Profits[i]);
        }

        // 生成小根堆和大根堆
        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        //将所有的项目放进小根堆
        for (int i = 0; i < nodes.length; i++) {
            minCostQ.add(nodes[i]);
        }

        // 最多做 k 个项目
        for (int i = 0; i < k; i++) {
            // 将小根堆中可以被解锁的项目进入到大根堆
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= w) {
                maxProfitQ.add(minCostQ.poll());
            }
            // 做不到 k 个项目的时候就停止，即初始资金没有办法解锁新的项目了
            if (maxProfitQ.isEmpty()) {
                return w;
            }
            w += maxProfitQ.poll().p;
        }
        return w;
    }
}
