package SwordToOfferSolution._18_01_DeleteNodeInList;

/**
 * 在 O(1) 的时间内删除链表节点
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

	public void inO1DeleteNodeInList(ListNode head, ListNode toBeDeleted) {
		if (head == null || toBeDeleted == null) return;

		// 要删除的节点不是尾节点
		if (toBeDeleted.next != null) {
			ListNode p = toBeDeleted.next;
			toBeDeleted.val = p.val;
			toBeDeleted.next = p.next;
		}
		// 链表只有一个节点，要删除的节点是头节点或尾节点
		else if (head == toBeDeleted) {
			head = null;
		}
		// 链表中有多个节点，删除尾节点
		else {
			ListNode cur = head;
			while (cur.next != toBeDeleted) cur = cur.next;
			cur.next = null;
		}
	}
}
