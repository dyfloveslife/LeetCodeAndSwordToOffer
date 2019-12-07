package SwordToOfferSolution._30_MinInStack;

import java.util.Stack;

/*
 * 包含 min 函数的栈
 * 思路：
 * 可以设置一个辅助栈，将每次最小的元素（之前最小的元素与新加入的元素，这两者之间的最小值）都保存起来。
 * 1. 设置一个 data 栈和一个 min 栈，入栈的时候，min 随着 data 一起增长；
 * 2. 压入 data 栈的栈顶元素和 min 的栈顶进行比较，如果 data.peek() < min.peek()，则将当前数同时压入 min 栈，
 *    否则重复压入 min 栈的栈顶；
 * 3. min 栈中的栈顶就是此时 data 栈中的最小元素；
 * 4. 弹出的时候，两个栈的栈顶都同时弹出。
 */
public class Solution {
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int newNum) {
        dataStack.push(newNum);
        // 如果辅助栈为空，或者新加入的元素比辅助栈的栈顶小，则将新加入的元素入辅助栈
        if (minStack.isEmpty() || newNum < minStack.peek()) {
            minStack.push(newNum);
        }
        // 如果新加入的元素比辅助栈的栈顶大，则将辅助栈的栈顶再次加入到辅助栈里面
        else {
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        if (!dataStack.isEmpty() && !minStack.isEmpty()) {
            dataStack.pop();
            minStack.pop();
        }
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}

