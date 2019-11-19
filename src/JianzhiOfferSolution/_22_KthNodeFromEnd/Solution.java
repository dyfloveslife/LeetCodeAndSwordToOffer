package _22_KthNodeFromEnd;

/**
 * 求链表中倒数第 K 个节点
 */
class ListNode {
	int val;
	ListNode next = null;

	ListNode(int val) {
		this.val = val;
	}
}

public class Solution {
	public ListNode findKthToTail(ListNode head, int k) {
		if (head == null || k == 0) return null;

		ListNode aHead = head;
		ListNode bBehind = null;

		for (int i = 0; i < k - 1; i++) {
			if (aHead.next != null) aHead = aHead.next;
			else return null;
		}
		bBehind = head;

		while (aHead.next != null) {
			aHead = aHead.next;
			bBehind = bBehind.next;
		}
		return bBehind;
	}
}
