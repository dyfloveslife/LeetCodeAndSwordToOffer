package LeetCodeSolution.DataStructure._08_LinkedList._86_PartitionList;

/*
 * 分割链表
 *
 * 题目描述：
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 思路：
 * 1. 遍历一遍链表，同时收集小于 x 的节点以及大于等于 x 的节点；
 * 2. 因此，采用两个 dummy，分别收集这两部分的链表；
 * 3. 相当于组成两个新的链表，一个链表中的节点都是小于 x 的，另一个链表中的节点都是大于等于 x 的；
 * 4. 然后让小于 x 的链表的最后一个节点指向另一个链表的第一个节点；
 * 5. 最后再将大于等于 x 的那个链表的最后一个节点的 next 指为 null 即可。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        ListNode dummy1 = new ListNode(-1);
        ListNode minPre = dummy1;
        ListNode dummy2 = new ListNode(-1);
        ListNode maxPre = dummy2;

        ListNode cur = head;

        while (cur != null) {
            if (cur.val < x) {
                minPre.next = cur;
                minPre = cur;
            } else {
                maxPre.next = cur;
                maxPre = cur;
            }
            cur = cur.next;
        }
        minPre.next = dummy2.next;
        maxPre.next = null;
        return dummy1.next;
    }
}
