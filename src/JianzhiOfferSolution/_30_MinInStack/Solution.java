package JianzhiOfferSolution._30_MinInStack;

import java.util.Stack;

/**
 * 包含 min 函数的栈
 * 可以设置一个辅助栈，将每次最小的元素（之前最小的元素与新加入的元素，这两者之间的最小值）都保存起来。
 */
public class Solution {
	private Stack<Integer> dataStack = new Stack<>();
	private Stack<Integer> minStack = new Stack<>();

	public void push(int node) {
		dataStack.push(node);

		// 如果辅助栈为空，或者新加入的元素比辅助栈的栈顶小，则将新加入的元素入辅助栈
		if (minStack.isEmpty() || node < minStack.peek())
			minStack.push(node);
			// 如果新加入的元素比辅助栈的栈顶大，则将辅助栈的栈顶再次加入到辅助栈里面
		else
			minStack.push(minStack.peek());
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

