package SwordToOfferSolution._09_QueueWithTwoStacks;

import java.util.Stack;

/*
 * 用两个栈实现队列
 * 原则：
 * 1) 如果 stackPush 决定要把元素往 stackPop 里送的话，那么要一次性的送全部送完；
 * 2) 如果 stackPop 里面有东西，则 stackStack 一定不要送。
 */
public class Solution {
    Stack<Integer> stackPush = new Stack<>();
    Stack<Integer> stackPop = new Stack<>();

    public void push(int node) {
        stackPush.push(node);
    }

    public int poll() {
        if (stackPop.isEmpty() && stackPush.isEmpty()) {
            throw new RuntimeException("Queue is empty.");
        }
        else if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }

    public int peek() {
        if (stackPop.isEmpty() && stackPush.isEmpty()) {
            throw new RuntimeException("Queue is empty.");
        } else if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();
    }
}
