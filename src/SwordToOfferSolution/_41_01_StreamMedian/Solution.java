package SwordToOfferSolution._41_01_StreamMedian;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 数据流中的中位数
 * 思路：
 * 1. 将整个数据分成两部分，左部分从左到右逐渐增大并存入大顶堆，右部分从左到右逐渐增大并存入小顶堆，即大顶堆中所有的数都小于小顶堆中的数；
 * 2. 若数据量为偶数，则大顶堆的堆顶和小顶堆的堆顶之和的平均值就是中位数；若数据量为奇数，则小顶堆的堆顶就是中位数；
 * 3. 为了保证数据平均分配到两个堆中，即两堆中数据的数量之差不能超过 1，即保持平衡状态；
 * 4. 如果规定数据量是偶数的时候将新数据插入到小顶堆，否则插入到大顶堆，但如果新插入的数比大顶堆中的一些数要小呢？
 *      可以先将这个新的数据插入到大顶堆，然后将大顶堆中的堆顶插入到小顶堆，这样就能保持左半部分一直小于右半部分了。
 * 5. 如果数据总数是偶数的话，则先将新数据插入到大顶堆中，然后再将大顶堆中的堆顶加入到小顶堆中；
 * 6. 如果数据总数是奇数的话，则先将新数据插入到小顶堆中，然后再将小顶堆中的堆顶加入到大顶堆中。
 */
public class Solution {
    // 默认是 小顶堆
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // 实现 大顶堆
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });

    // 记录数据的数量是奇数个还是偶数个
    private int count = 0;

    // 插入从数据流中读出来的数据
    private void insert(Integer num) {
        // 数据总量为偶数
        if ((count & 1) == 0) {
            maxHeap.offer(num);
            int max = maxHeap.poll();
            minHeap.offer(max);
        } else {    // 数据总量是奇数
            minHeap.offer(num);
            int min = minHeap.poll();
            maxHeap.offer(min);
        }
        count++;
    }

    // 得到已有所有数据的中位数
    private Double getMedian() {
        if ((count & 1) == 0)
            return new Double(maxHeap.peek() + minHeap.peek()) / 2;
        else
            return new Double(minHeap.peek());
    }
}
