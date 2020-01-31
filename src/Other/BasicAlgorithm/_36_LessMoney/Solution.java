package Other.BasicAlgorithm._36_LessMoney;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * 分割金条的最小代价
 *
 * 题目描述：
 * 一块金条分成两半，需要花费和长度数值一样的铜板。例如，长度为 20 的金条，不管分成任意长度的两半，
 * 都需要花费 20 个铜板。现有一群人想划分整块金条，问怎么划分最省铜板，即最省钱？
 *
 * 例如，给定数组 {10, 20, 30}，代表一共三个人，整块金条的长度为 10+20+30=60，金条要分成 10、20、30 三个部分。
 * 如果先把长度为 60 的金条分成 10 和 50 两部分，则需要花费 60 个铜板，然后再把长度 50 的金条划分成 20 和 30 两部分，
 * 则需要 50 个铜板，总的铜板数是 110。
 *
 * 但是，如果先把长度为 60 的金条分成 30 和 30 两部分，则需要花费 60 个铜板，然后再把长度 30 的金条划分成 10 和 20 的，
 * 则需要 30 个铜板，总的铜板数是 90 个。
 *
 * 输入一个数组，求分割后的最小代价。
 *
 * 思路：
 * 1. 哈夫曼编码问题；
 * 2. 将数组放进小根堆中，每次从小根堆拿出两个数，相加产生一个代价，再将这个代价放进小根堆里去；
 * 3. 再从小根堆中拿出两个数，相加产生一个代价，再放进小根堆中；
 * 4. 等到堆中只剩一个数的时候，将之前所有的代价加起来，就是最小代价;
 * 5. 这里使用的就是贪心，从所有数中选出的总是最小的；
 * 6. 通过比较器来实现不同的堆。
 */
public class Solution {

    public static int lessMoney(int[] arr) {
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        // 只要小根堆中的数还有两个及两个以上，就不断的拿出来进行求和
        while (pQ.size() > 1) {
            cur = pQ.poll() + pQ.poll();
            sum += cur;
            pQ.add(cur);
        }
        return sum;
    }

    // 实现一个小根堆的比较器
    public static class MinheapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            // o1 < o2 是负数
            return o1 - o2;
        }
    }

    public static void main(String[] args) {
        int[] arr = {6, 7, 8, 9};
        System.out.println(lessMoney(arr));

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new MinheapComparator());
        for (int num : arr) {
            priorityQueue.add(num);
        }
        while (!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.poll() + " ");
        }
    }
}
