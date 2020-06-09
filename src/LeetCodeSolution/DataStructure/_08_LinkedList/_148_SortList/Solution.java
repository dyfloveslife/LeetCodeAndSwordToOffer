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
    class ListNode {
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

        pre.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        return mergeList(left, right);
    }

    // 合并两个链表
    private ListNode mergeList(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = (l1 == null) ? l2 : l1;
        return dummy.next;
    }
}
