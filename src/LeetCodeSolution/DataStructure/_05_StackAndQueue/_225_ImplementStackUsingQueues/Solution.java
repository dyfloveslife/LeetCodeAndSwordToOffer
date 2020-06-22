package LeetCodeSolution.DataStructure._05_StackAndQueue._225_ImplementStackUsingQueues;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 用队列实现栈
 *
 * 题目描述：
 * 使用队列实现栈的下列操作：
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 *
 * 思路：
 * 1. 还是使用两个队列进行实现，只不过一个是普通的队列，另一个需要使用双端队列；
 * 2. 普通队列正常入队，正常出队，而双端队列在入队的时候需要从尾部入队；
 * 3. 也是理清楚这两个队列之间的逻辑关系即可。
 */
public class Solution {
    class MyStack {

        private Queue<Integer> q1;
        private LinkedList<Integer> q2;

        public MyStack() {
            q1 = new LinkedList<>();
            q2 = new LinkedList<>();
        }

        public void push(int x) {
            q1.offer(x);
            q2.addFirst(x);
        }

        public int pop() {
            if (q1.isEmpty() && q2.isEmpty()) {
                return -1;
            }
            q1.poll();
            return q2.pollFirst();
        }

        public int top() {
            if (q2.isEmpty()) {
                return -1;
            }
            return q2.peekFirst();
        }

        public boolean empty() {
            return q1.isEmpty() && q2.isEmpty();
        }
    }
}
