package LeetCodeSolution.DataStructure._08_LinkedList._148_SortList;

/*
 * 排序链表
 *
 * 题目描述：
 * 在 O(nlogn) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 思路：
 * 1. 既然要求时间复杂度和空间复杂度是 O(nlogn) 的，自然想到的是归并排序；
 * 2. 首先可以使用快慢指针来确定链表的中点，以便于进行二路归并排序；
 * 3. 由于需要断开链表，因此还需要一个指针，指向中间节点的前驱。
 */
public class Solution {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode sortList(ListNode head) {
        // 注意：由于使用了递归，所以这里和以前的特判方式有所不同
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // 经过以上的操作，对于奇数个节点的链表来说，pre 最终会指向中间节点的前一个节点，
        // slow 最终会指向中间节点，fast 最终会指向最后一个节点。
        // 而对于偶数个节点来说，pre 最终会指向两个中间节点的前面节点，
        // slow 最终会指向两个中间节点的后面节点，fast 最终会指向 null。

        // 接下来，断开链表
        pre.next = null;

        // 通过递归的方式得到两个断开的链表的头节点
        ListNode head1 = sortList(head);
        ListNode head2 = sortList(slow);

        return mergeList(head1, head2);
    }

    // 合并两个链表
    private ListNode mergeList(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }

        cur.next = (head1 == null) ? head2 : head1;
        return dummy.next;
    }
}
