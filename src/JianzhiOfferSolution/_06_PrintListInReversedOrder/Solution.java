package _06_PrintListInReversedOrder;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 题目描述：
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 */
public class Solution {

	public static class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
			this.next = null;
		}
	}

	// 非递归实现
	public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
		Stack<Integer> stack = new Stack<>();
		ArrayList<Integer> list = new ArrayList<>();
		while (listNode != null) {
			stack.push(listNode.val);
			listNode = listNode.next; //后移
		}
		while (!stack.isEmpty()) {
			list.add(stack.pop()); //出栈的时候需要放在 list 中
		}
		return list;
	}

	// 递归实现
	static ArrayList<Integer> list_Recursively = new ArrayList<>();

	public static ArrayList<Integer> printListFromTailToHead_Recursively(ListNode listNode) {
		if (listNode != null) {
			printListFromTailToHead_Recursively(listNode.next);
			list_Recursively.add(listNode.val);
		}
		return list_Recursively;
	}

	public static void main(String[] args) {
		ListNode listNode = new ListNode(0);
		ListNode node = listNode;
		for (int i = 1; i < 10; i++) {
			node.next = new ListNode(i);
			node = node.next;
		}
		ArrayList<Integer> list = printListFromTailToHead_Recursively(listNode);
		System.out.println(list);
	}
}
