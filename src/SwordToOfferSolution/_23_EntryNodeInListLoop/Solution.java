package SwordToOfferSolution._23_EntryNodeInListLoop;

/**
 * 环形链表
 * <p>
 * 题目描述：
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * <p>
 * 思路：
 * 1、可以使用哈希表，在来到下一个节点的时候，判断是否已经出现过；
 * 2、使用「龟兔赛跑」的思想，兔子先跑，乌龟在后，假设链表中存在环，那么兔子会一直处于环中，在某一个时间点，兔子和乌龟一定会相遇。
 */
public class Solution {
    static class ListNode {
        private int val;
        private ListNode next;

        private ListNode(int val) {
            this.val = val;
        }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head, fast = head;
        while (fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }
}
