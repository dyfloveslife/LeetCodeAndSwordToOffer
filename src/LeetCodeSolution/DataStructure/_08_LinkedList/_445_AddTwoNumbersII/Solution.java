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
    static class ListNode {
        private int val;
        private ListNode next;

        private ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 使用两个栈
     *
     * @param l1 ListNode
     * @param l2 ListNode
     * @return ListNode
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        ListNode cur = l1;
        while (cur != null) {
            s1.push(cur.val);
            cur = cur.next;
        }
        cur = l2;
        while (cur != null) {
            s2.push(cur.val);
            cur = cur.next;
        }

        // 结果链表的头节点
        ListNode head = null;
        int carry = 0;
        while (!s1.isEmpty() || !s2.isEmpty() || carry > 0) {
            int n1 = !s1.isEmpty() ? s1.pop() : 0;
            int n2 = !s2.isEmpty() ? s2.pop() : 0;
            int sum = n1 + n2 + carry;
            carry = sum / 10;

            // 头插法
            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
        }

        return head;
    }

    /**
     * 通过反转链表实现
     *
     * @param l1 ListNode
     * @param l2 ListNode
     * @return ListNode
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int n1 = (l1 != null) ? l1.val : 0;
            int n2 = (l2 != null) ? l2.val : 0;
            int sum = n1 + n2 + carry;
            carry = sum / 10;

            cur.next = new ListNode(sum % 10);
            cur = cur.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        return reverseList(dummy.next);
    }

    /**
     * 直接在 while 中使用头插法
     *
     * @param l1 ListNode
     * @param l2 ListNode
     * @return ListNode
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);

        ListNode result = null;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int n1 = (l1 != null) ? l1.val : 0;
            int n2 = (l2 != null) ? l2.val : 0;
            int sum = n1 + n2 + carry;
            carry = sum / 10;

            ListNode newNode = new ListNode(sum % 10);
            // 头插法
            newNode.next = result;
            result = newNode;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        return result;
    }

    /**
     * 迭代式反转单链表
     *
     * @param head ListNode
     * @return ListNode
     */
    private ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            // 保存当前节点的下一个节点
            ListNode next = cur.next;
            // 修改当前节点的 next 指针
            cur.next = pre;
            // 后移
            pre = cur;
            cur = next;
        }

        return pre;
    }
}
