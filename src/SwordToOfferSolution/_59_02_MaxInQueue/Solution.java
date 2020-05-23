package SwordToOfferSolution._59_02_MaxInQueue;

import java.util.LinkedList;

/*
 * 队列的最大值
 *
 * 题目描述：
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 思路：
 * 1. 另开一个双向队列 help，用于把当前 data 队列中的最大值存储到 help 中的队头处；
 * 2. 如果求当前队列的最大值，则只需要返回 help 队列的队头即可；
 * 3. 对于入队的逻辑，首先让 value 正常入 data 队列，然后判断 value 和 help 队列中队尾元素的大小关系；
 *    由于在 help 中，越是靠近队头的元素越大，因此，如果 value 大于 help 队列中队尾元素的话，则需要把 help 队列中队尾元素一直弹出；
 * 4. 对于出队逻辑，首先将 data 队列中的队头弹出，然后将它与 help 的队头比较：
 *    如果相等，则需要将 help 的队头一起弹出，目的为了维护当前队列的最大值。
 */
public class Solution {
    class MaxQueue {
        LinkedList<Integer> data;
        LinkedList<Integer> help;

        public MaxQueue() {
            data = new LinkedList<>();
            help = new LinkedList<>();
        }

        public int max_value() {
            if (data.isEmpty()) {
                return -1;
            }

            return help.peekFirst();
        }

        public void push_back(int value) {
            // 先正常入队，即新来的元素从队尾进入
            data.offerLast(value);
            // 这里需要注意的是：如果新添加的元素比 help.peekLast() 大，
            // 则将 help.removeLast() 弹出。
            // 这是因为 help 队列中的队头保存的是当前 data 队列中最大的元素
            while (!help.isEmpty() && value > help.peekLast()) {
                help.pollLast();
            }
            help.offerLast(value);
        }

        public int pop_front() {
            if (data.isEmpty()) {
                return -1;
            }

            // 先将 data 中队头的元素取出来
            int value = data.pollFirst();
            // 然后再看看这个队头元素是不是 help 中的队头元素，
            // 如果是的话，则需要弹出 help 中的队头元素，以便于更新当前队列的最大值
            if (help.peekFirst() == value) {
                help.pollFirst();
            }
            return value;
        }
    }
}
