package JianzhiOfferSolution._25_MergeSortedLists;

/**
 * 合并两个单链表
 * 如果用迭代的方式实现的话，就先创建一个节点，然后使用两个指针比较大小后将小的加入到新建节点后。
 * 最后如果其中一个链表多出来的话，要全部加入到新建链表的最后。
 * 递归实现：
 */

class ListNode {
	int val;
	ListNode next = null;

	ListNode(int val) {
		this.val = val;
	}
}

public class Solution {
	public ListNode mergeList(ListNode head1, ListNode head2) {
		if (head1 == null) return head2;
		if (head2 == null) return head1;

		ListNode mergedHead = null;

		if (head1.val < head2.val) {
			mergedHead = head1;
			mergedHead.next = mergeList(head1.next, head2);
		} else {
			mergedHead = head2;
			mergedHead.next = mergeList(head1, head2.next);
		}
		return mergedHead;
	}
}
