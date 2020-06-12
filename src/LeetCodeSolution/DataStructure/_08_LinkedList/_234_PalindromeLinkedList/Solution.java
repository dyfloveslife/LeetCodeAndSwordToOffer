package LeetCodeSolution.DataStructure._08_LinkedList._234_PalindromeLinkedList;

import java.util.Stack;

/*
 * 回文链表
 *
 * 题目描述：
 * 请判断一个链表是否为回文链表。
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 思路：
 * 1. 如果不使用进阶的方法，则可以将节点放入栈中，然后在依次弹出的过程中和原链表中的节点进行比较；
 * 2. 如果想要满足进阶的要求，则需要用到快慢指针，让慢指针指向链表中间的节点，然后再将后半部分节点反转；
 * 3. 最后头尾两个指针一一进行比较即可。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 使用快慢指针
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;
        // 快慢指针，对于奇数个节点，slow 会来到链表的中间节点
        // 对于偶数个节点，slow 会来到相同节点的第一个节点
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 这里为了奇偶链表的统一，直接反转相同节点的下一个节点
        slow = reverseList(slow.next);
        ListNode cur = head;
        // 对于偶数个节点来说，这两个链表的长度是一样的，
        // 对于奇数个节点来首，反转后的链表少一个，
        // 因此下面的 while 是以 slow 为标志进行循环的，这一点需要注意
        while (slow != null) {
            if (cur.val != slow.val) {
                return false;
            }
            cur = cur.next;
            slow = slow.next;
        }
        return true;
    }

    private ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode nex  = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }
        return pre;
    }

    // 使用 stack
    public boolean isPalindrome1(ListNode head) {
        if (head == null) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }

        cur = head;
        while (!stack.isEmpty()) {
            if (stack.pop() != cur.val) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }
}
