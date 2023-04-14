package SwordToOfferSolution._41_01_StreamMedian;

import java.util.PriorityQueue;

/*
 * 数据流中的中位数
 *
 * 题目描述：
 * 如何得到一个数据流中的中位数？
 * 如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *
 * 思路：
 * 1. 使用大根堆存储数据流中较小的 n/2 个元素，其中堆顶为这 n/2 个元素的最大值；
 * 2. 使用小根堆存储数据流中较大的 n/2 个元素，其中堆顶为这 n/2 个元素的最小值；
 * 3. 因此，这里的奇偶个数十分重要：
 *    如果是偶数的话，那么当前的中位数就是两个堆的堆顶元素之和，再取平均值；
 *    如果是奇数的话，则只要保证大根堆的元素个数永远比小根堆的元素个数多一个，那么大根堆的堆顶元素就是当前数据流的中位数。
 * 4. 大根堆的堆顶元素始终小于或等于小根堆的堆顶元素；
 * 5. 大根堆的元素个数等于小根堆的元素个数，或比小根堆的元素个数多 1。
 */
public class Solution {

    static class MedianFinder {
        private int count;
        private PriorityQueue<Integer> minHeap;
        private PriorityQueue<Integer> maxHeap;

        public MedianFinder() {
            this.minHeap = new PriorityQueue<>();
            this.maxHeap = new PriorityQueue<>((o1, o2) -> (o2 - o1));
        }

        public void addNum(int num) {
            count += 1;
            // 先进大根堆
            maxHeap.offer(num);
            // 为了保证 maxHeap 中的元素始终大于等于 minHeap 中的元素
            minHeap.offer(maxHeap.poll());
            // 如果两个堆中的元素数量之和为奇数，则小根堆需要弹出元素放进大根堆
            // 让大根堆始终比小根堆多一个元素
            if (count % 2 == 1) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if (count % 2 == 0) {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            } else {
                return maxHeap.peek().doubleValue();
            }
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        medianFinder.addNum(3);

        System.out.println(medianFinder.findMedian()); // 2.0
    }
}