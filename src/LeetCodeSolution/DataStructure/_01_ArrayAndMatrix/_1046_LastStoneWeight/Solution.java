package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._1046_LastStoneWeight;

import java.util.PriorityQueue;

/*
 * 最后一块石头的重量
 *
 * 题目描述：
 * 有一堆石头，每块石头的重量都是正整数。
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。
 * 注意：返回此石头的重量。
 * 如果没有石头剩下，就返回 0。
 *
 * 思路：
 * 1. 由于需要动态地维护数组中元素的大小关系，因此可以使用堆；
 * 2. 为什么使用堆呢？
 * 3. 因为每次都需要从数组中选择两个最大的元素，然后进行相减；
 * 4. 那么“从数组中选择两个最大的元素”的这个动作就可以使用大根堆来完成。
 */
public class Solution {
    public int lastStoneWeight(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }

        // 定义大根堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(stones.length, ((o1, o2) -> (o2 - o1)));
        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        while (maxHeap.size() >= 2) {
            Integer max1 = maxHeap.poll();
            Integer max2 = maxHeap.poll();
            maxHeap.offer(max1 - max2);
        }
        return maxHeap.poll();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] stones = {2, 7, 4, 1, 8, 1};

        System.out.println(solution.lastStoneWeight(stones));
    }
}
