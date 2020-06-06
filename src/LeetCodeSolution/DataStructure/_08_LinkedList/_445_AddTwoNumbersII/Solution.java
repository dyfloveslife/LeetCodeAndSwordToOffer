package LeetCodeSolution.DataStructure._08_LinkedList._445_AddTwoNumbersII;

import java.util.Stack;

/*
 * 两数相加Ⅱ
 *
 * 题目描述：
 * 给你两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 进阶：
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 * 思路：
 * 1. 如果可以进行链表反转的话，则只需要将给定的链表进行反转后，再进行相加；
 * 2. 最后再将求和之后的链表再次反转；
 * 3. 如果不可以进行反转的话，则需要用到栈；
 * 4. 每次弹栈的时候，就相当于取了最后一个节点，然后每得到一个值（节点），就使用头插法往前插入即可。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 不对链表进行修改，而是使用栈
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }

        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        ListNode head = null;
        int carry = 0;
        while (!s1.isEmpty()|| !s2.isEmpty()) {
            int n1 = !s1.isEmpty() ? s1.pop() : 0;
            int n2 = !s2.isEmpty() ? s2.pop() : 0;
            int sum = n1 + n2 + carry;
            carry = sum / 10;
            sum = sum % 10;

            // 头插法
            ListNode node = new ListNode(sum);
            node.next = head;
            head = node;
        }
        if (carry == 1) {
            ListNode node = new ListNode(1);
            node.next = head;
            head = node;
        }
        return head;
    }

    // 如果可以对原链表进行反转的话
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        l1 = reverseList(l1);
        l2 = reverseList(l2);

        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = (l1 != null) ? l1.val : 0;
            int n2 = (l2 != null) ? l2.val : 0;
            int sum = n1 + n2 + carry;
            carry = sum / 10;
            sum = sum % 10;

            pre.next = new ListNode(sum);
            pre = pre.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry == 1) {
            pre.next = new ListNode(1);
        }
        // 最后记得再将链表反转一次
        return reverseList(dummy.next);
    }

    // 单纯的反转链表
    private ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = null;
        ListNode pre = dummy;

        ListNode cur = head;
        while (cur != null) {
            ListNode nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }

        return pre;
    }
}
