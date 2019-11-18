package _18_02_DeleteDuplicatedNode;

public class Solution {
	public class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
			this.next = null;
		}
	}

	public ListNode deleteDuplication(ListNode pHead) {
		if (pHead == null || pHead.next == null) return pHead;

		// 定义一个虚拟头节点dummy，指向链表的头节点。
		// 这个dummy保证不会被删掉，这种情况下就可以不用管头节点有可能被删掉的情况了
		ListNode dummy = new ListNode(-1);
		dummy.next = pHead;

		ListNode last = dummy;  // 指向上一个有效的节点
		ListNode temp = pHead;   // 指向当前遍历的节点
		while (temp != null && temp.next != null) {
			if (temp.val == temp.next.val) {    // 如果有相同的
				while (temp.next != null && temp.val == temp.next.val) // 如果相同，则后移temp，一直到temp和temp.next不同为止
					temp = temp.next;
				last.next = temp.next; // 到这里说明temp和temp.next不同了，则找到不用删除的节点，例如从 11223 中找到 3
				temp = temp.next;
			} else {    // 如果没有相同的
				last = temp; //当前节点就不用删除（这里last要指向temp，因为temp已经和temp.next不同了，就用last指向该有效的节点）
				temp = temp.next;
			}
		}
		return dummy.next;
	}
}
