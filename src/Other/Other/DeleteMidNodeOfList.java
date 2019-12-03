package Other.Other;

/**
 * 删除链表的中间节点
 */
public class DeleteMidNodeOfList {
	class ListNode{
		int val;
		ListNode next;
		ListNode(int val) {
			this.val = val;
		}
	}

	public static ListNode deleteMidNodeOfList(ListNode head) {
		if (head == null) return null;

		ListNode fastNode = head;
		ListNode slowNode = head;
		while (fastNode != null && fastNode.next != null) {
			fastNode = fastNode.next.next;
			slowNode = slowNode.next;
		}
		return slowNode;
	}
}
