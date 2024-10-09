package SwordToOfferSolution._09_QueueWithTwoStacks;

import java.util.Stack;

/*
 * 用两个栈实现队列
 *
 * 题目描述：
 * 请实现两个函数，分别完成在队列尾部插入结点 appendTail 和在队列头部删除结点 deleteHead 的功能。
 *
 * 思路：
 * 1. 如果 stackPush 决定要把元素往 stackPop 里送的话，那么要一次性的送全部送完；
 * 2. 如果 stackPop 里面有东西，则 stackPush 一定不要送。
 */
public class Solution {
    static class MyQueue {
        Stack<Integer> stackPush;
        Stack<Integer> stackPop;

        public MyQueue() {
            stackPush = new Stack<>();
            stackPop = new Stack<>();
        }

        public void appendTail(int value) {
            stackPush.push(value);
        }

        public int deleteHead() {
            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }

            return stackPop.pop();
        }

        public int peek() {
            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }

            return stackPop.peek();
        }

        public boolean empty() {
            return stackPop.isEmpty() && stackPush.empty();
        }
    }
}
