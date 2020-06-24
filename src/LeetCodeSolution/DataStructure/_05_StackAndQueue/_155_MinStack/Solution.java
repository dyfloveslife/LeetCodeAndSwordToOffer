package LeetCodeSolution.DataStructure._05_StackAndQueue._155_MinStack;

import java.util.Stack;

/*
 * 最小栈
 *
 * 题目描述：
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 * 思路：
 * 1. 维护两个栈即可。
 */
public class Solution {
    class MinStack {

        private Stack<Integer> s1;
        private Stack<Integer> s2;

        public MinStack() {
            s1 = new Stack<>();
            s2 = new Stack<>();
        }

        public void push(int x) {
            s1.push(x);
            if (s2.isEmpty() || x < s2.peek()) {
                s2.push(x);
            } else {
                s2.push(s2.peek());
            }
        }

        public void pop() {
            if (!s1.isEmpty() && !s2.isEmpty()) {
                s1.pop();
                s2.pop();
            }
        }

        public int top() {
            if (!s1.isEmpty()) {
                return s1.peek();
            }
            return -1;
        }

        public int getMin() {
            if (!s2.isEmpty()) {
                return s2.peek();
            }
            return -1;
        }
    }
}
