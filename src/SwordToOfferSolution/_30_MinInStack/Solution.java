package SwordToOfferSolution._30_MinInStack;

import java.util.Stack;

/*
 * 包含 min 函数的栈
 *
 * 题目描述：
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数。
 * 在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 * 思路：
 * 可以设置一个辅助栈，将每次最小的元素（之前最小的元素与新加入的元素，这两者之间的最小值）都保存起来。
 * 1. 设置一个 data 栈和一个 min 栈，入栈的时候，min 随着 data 一起增长；
 * 2. 压入 data 栈的栈顶元素和 min 的栈顶进行比较，如果 data.peek() < min.peek()，则将当前数同时压入 min 栈，
 *    否则重复压入 min 栈的栈顶；
 * 3. min 栈中的栈顶就是此时 data 栈中的最小元素；
 * 4. 弹出的时候，两个栈的栈顶都同时弹出。
 */
public class Solution {

    class MainStack {

        Stack<Integer> stack1;
        Stack<Integer> stack2;

        public MainStack() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void push(int x) {
            stack1.push(x);
            // 如果辅助栈为空，或者新加入的元素比辅助栈的栈顶小，则将新加入的元素入辅助栈
            if (stack2.isEmpty() || x < stack2.peek()) {
                stack2.push(x);
            // 如果新加入的元素比辅助栈的栈顶大，则将辅助栈的栈顶再次加入到辅助栈里面
            } else {
                stack2.push(stack2.peek());
            }
        }

        public void pop() {
            if (!stack1.isEmpty() && !stack2.isEmpty()) {
                stack1.pop();
                stack2.pop();
            }
        }

        public int top() {
            return stack1.peek();
        }

        public int min() {
            return stack2.peek();
        }
    }
}