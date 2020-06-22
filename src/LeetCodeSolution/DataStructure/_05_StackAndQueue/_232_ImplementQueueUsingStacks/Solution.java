package LeetCodeSolution.DataStructure._05_StackAndQueue._232_ImplementQueueUsingStacks;

import java.util.Stack;

/*
 * 用栈实现队列
 *
 * 题目描述：
 * 使用栈实现队列的下列操作：
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空
 *
 * 思路：
 * 1. 这里格外注意两个栈之间的逻辑顺序。
 */
public class Solution {
    class MyQueue {
        private Stack<Integer> s1;
        private Stack<Integer> s2;

        public MyQueue() {
            s1 = new Stack<>();
            s2 = new Stack<>();
        }

        public void push(int x) {
            s1.push(x);
        }

        public int pop() {
            // 只要 s2 为空，那么就需要将 s1 中的所有元素灌入到 s2 中
            if (s2.isEmpty()) {
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
            }
            return s2.pop();
        }

        public int peek() {
            if (s2.isEmpty()) {
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
            }
            return s2.peek();
        }

        public boolean empty() {
            return s1.isEmpty() && s2.isEmpty();
        }
    }
}
