package SwordToOfferSolution._22_KthNodeFromEnd;

/*
 * 求链表中倒数第 K 个节点
 *
 * 题目描述：
 * 输入一个链表，输出该链表中倒数第 k 个结点。为了符合大多数人的习惯，本题从 1 开始计数，即链表的尾结点是倒数第 1 个结点。
 * 例如一个链表有 6 个结点，从头结点开始它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个结点是值为 4 的结点。
 *
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
        if (head == null || k == 0) {
            return null;
        }

        ListNode aHead = head;
        ListNode bBehind = null;
        for (int i = 0; i < k - 1; i++) {
            if (aHead.next != null) {
                aHead = aHead.next;
            } else {
                return null;
            }
        }

        bBehind = head;
        while (aHead.next != null) {
            aHead = aHead.next;
            bBehind = bBehind.next;
        }
        return bBehind;
    }
}
