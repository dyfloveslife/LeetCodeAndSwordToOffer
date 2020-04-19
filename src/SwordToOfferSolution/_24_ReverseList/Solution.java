package SwordToOfferSolution._24_ReverseList;

/*
 * 反转单链表
 *
 * 题目描述：
 * 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
 *
 * 思路：
 * 1. 在某个链表调整指针的过程中，需要将当前节点的前一个节点和后一个节点进行保存，
 *    因为，如果不保存后一个节点的话，则当前节点会和后面的链表断开；
 * 2. 而反转后的链表的头节点就是原始链表中 next 为 null 的节点；
 * 3. 请画图进行理解。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverseList1(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    // 递归
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newList = reverseList2(head.next);
        ListNode t1 = head.next;
        t1.next = head;
        head.next = null;
        return newList;
    }
}
