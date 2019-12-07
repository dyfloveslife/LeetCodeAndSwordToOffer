package Other.BasicAlgorithm._16_StackAndQueueConvert;

import com.sun.org.apache.bcel.internal.generic.SWAP;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 用两个栈实现队列
 * https://github.com/dyfloveslife/LeetCodeAndSwordToOffer/blob/master/src/SwordToOfferSolution/_09_QueueWithTwoStacks/Solution.java
 *
 * 用两个队列实现栈
 * 思路：
 * 1. 设置一个 data 队列和一个 help 队列；
 * 2. data 队列中的数正常入队，出队的时候进入 help 队列，但是出的时候留下最后一个数，然后返回给用户，循环这种操作即可；
 * 3. 每次都需要将 data 和 help 交换一下引用。
 */
public class Solution {
    public static class TwoQueuesStack {
        private Queue<Integer> data;
        private Queue<Integer> help;

        public TwoQueuesStack() {
            data = new LinkedList<>();
            help = new LinkedList<>();
        }

        // 入栈
        public void push(int pushInt) {
            data.add(pushInt);
        }

        // 出栈
        public int pop() {
            if (data.isEmpty()) {
                throw new RuntimeException("Stack is empty.");
            }
            while (data.size() > 1) {
                help.add(data.poll());
            }
            int res = data.poll();
            swap();
            return res;
        }

        // 获取栈顶元素
        public int peek() {
            if (data.isEmpty()) {
                throw new RuntimeException("Stack is empty.");
            }
            while (data.size() != 1) {
                help.add(data.poll());
            }
            int res = data.poll();
            // 这里和 pop() 操作有一些区别
            // 在获取栈顶元素后，还需要将其加入到 help 队列中
            help.add(res);
            swap();
            return res;
        }

        // 交换 data 队列和 help 队列的引用
        private void swap() {
            Queue<Integer> temp = help;
            help = data;
            data = temp;
        }
    }
}
