package Other.Other;

/**
 * 判断链表是否有环
 */

class ListNode {
	int val;
	ListNode next = null;

	ListNode(int val) {
		this.val = val;
	}
}

public class ListHasCycle {
	public boolean hasCycle(ListNode head) {
		if (head == null) return false;
		ListNode fastNode = head;
		ListNode slowNode = head;
		while (fastNode != null && fastNode.next != null) {
			fastNode = fastNode.next.next;
			slowNode = slowNode.next;
			if (fastNode == slowNode) return true;
		}
		return false;
	}
}
